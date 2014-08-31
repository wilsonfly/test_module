#include <stdio.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#define FILE_NAME "a.out"

int main()
{
	int result;
	chmod (FILE_NAME,S_IRUSR|S_IWUSR|S_IXUSR|S_IRGRP|S_IWGRP|S_IXGRP); 

	if( !access(FILE_NAME,F_OK) )
		printf("%s exist\n", FILE_NAME);
	if( !access(FILE_NAME,R_OK|W_OK|X_OK) )
		printf("%s rwx is ok\n", FILE_NAME);

	mkdir("a_dir",S_IXUSR|S_IRUSR|S_IWUSR);

	return 0;
}
