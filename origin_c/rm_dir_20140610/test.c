#include <stdio.h>
#include "llog_priv.h"
#include "type.h"

#include <dirent.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>

#if 0
#ifndef bool
#define bool char
#define false 0
#define true 1
#endif
#endif

#define UNLUCKY_DIR "./unlucky_dir"

static void delete_contents_of_dir(const char* path);

int main()
{
    delete_contents_of_dir(UNLUCKY_DIR);
}

static bool is_special_dir(const char* path)
{
    return !strcmp(path,".") || !strcmp(path,"..");
}

static bool is_file(const char *path)
{
    struct stat statbuf;
    if( !lstat(path, &statbuf) )
        return S_ISREG(statbuf.st_mode);
    return false;
}

/*
 *rm the files/dirs in path
 */
static void delete_contents_of_dir(const char* path)
{
    DIR *dir = NULL;
    struct dirent* dir_info = NULL;
    char new_path[128];
    
    if( is_file(path) )
    {
        printf("[%s] is no dir \n", path);
        return;
    }

    if( (dir = opendir(path)) == NULL )
    {
        printf("opendir %s failed!\n", path);
        return;
    }

    while( (dir_info=readdir(dir)) != NULL )
    {
        if( is_special_dir(dir_info->d_name) )
            continue;

        memset(new_path,0,sizeof(new_path));
        strcpy(new_path,path);
        if( path[strlen(path)-1] != '/')
            strcat(new_path,"/");
        strcat(new_path,dir_info->d_name);
        //printf("now:%s\n", new_path);

        if(dir_info->d_type != DT_DIR)
        {
            remove(new_path);
            printf("rm file:%s\n", new_path);
        }
        else
        {
            delete_contents_of_dir(new_path);
            rmdir(new_path);
            printf("rm dir:%s\n", new_path);
        }
    }
}
