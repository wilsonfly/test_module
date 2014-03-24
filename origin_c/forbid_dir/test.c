/*
 * This test try to forbin doing anything to the files in /dev.
 * So try to get the absolute dir like /dev/file_no1,or ./file_no2 when pwd is /dev/.....
 */
#include <stdio.h>
#include <stdlib.h> 
#include <unistd.h> 
#include <string.h>
#include "llog_priv.h"

#ifndef PATH_LENGTH_MAX 
#define PATH_LENGTH_MAX 1024 
#endif 

static char test_str[][32]=
{
	"/dev/../dev/./tt/a.c",
	"/dev/../dev/././tt/a.c",
	"./a.c",
	"../a.c",
	"/tmp/a.c",
};

#define FORBIDON_DIR "/dev"

static int get_exe_dir();
static int get_cwd(char* buf, int length_max);
static void remove_dot(char* buf);

int main( void ) 
{ 
	char exe_dir[PATH_LENGTH_MAX];
	char current_dir[PATH_LENGTH_MAX];
	char final_file_name[PATH_LENGTH_MAX];
	char* p = NULL;
	unsigned int i = 0;

	memset(exe_dir, 0, sizeof(exe_dir));
	get_exe_dir(exe_dir,PATH_LENGTH_MAX); 
	log("%s",exe_dir); 
	p = strrchr(exe_dir,'/');
	if( p )
		*p = '\0';
	log("%s",exe_dir);

	get_cwd(current_dir,PATH_LENGTH_MAX);
	log("current_dir=%s",current_dir);

	for( i=0; i<sizeof(test_str)/sizeof(test_str[0]); i++ )
	{
		log("\nnum:%d", i);
		log("origin=%s",test_str[i]);

		memset(final_file_name, 0, PATH_LENGTH_MAX);

		if( test_str[i][0] != '/' )
		{
			strncpy(final_file_name, current_dir, PATH_LENGTH_MAX);
			strncat(final_file_name, "/", PATH_LENGTH_MAX);
			strncat(final_file_name, test_str[i], PATH_LENGTH_MAX);
			remove_dot(final_file_name);
			log("not from /,final=%s",final_file_name);
		}
		else
		{
			strncpy(final_file_name, test_str[i], PATH_LENGTH_MAX);
			remove_dot(final_file_name);
			log("from /,final=%s",final_file_name);
		}
		if(/* !strncmp(current_dir, FORBIDON_DIR, sizeof(FORBIDON_DIR)-1) ||*/
				!strncmp(final_file_name, FORBIDON_DIR, sizeof(FORBIDON_DIR)-1) )
			log("-_-");
	}

	return 0; 
}

static int get_exe_dir(char* buf, int length_max)
{ 
	int ret;

	ret = readlink("/proc/self/exe", buf, length_max); 
	if ( ret < 0 )
	{ 
		log("readlink error !!");
		return -1; 
	} 
	buf[ret] = '\0'; 

	return 0; 
} 

static int get_cwd(char* buf, int length_max)
{
	char* ret = NULL;
	ret =  getcwd(buf,length_max);
	if( !ret )
	{
		log("getcwd error !!");
		return -1;
	}
	return 0;
}

static void remove_dot(char* buf)
{
	char* p = NULL;
	char* q = NULL;

//ex:	/dev/../dev/./../tt/a.c
//ex:	../dev/./../tt/a.c
//ex:	./a.c
//ex:	../a.c
	while( p=strstr(buf,"./") )
	{
		if( *(p-1) == '.' && *(p-2) == '/')
		{
			*(p-2) = '\0';
			if( q=strrchr(buf,'/') )
				*(q+1) = '\0';
		}
		else if( *(p-1) == '.' )
		{
			*(p-1) = '\0';
		}
		else
		{
			*(p) = '\0';
		}

		strncat(buf,p+2,PATH_LENGTH_MAX);
	}
}
