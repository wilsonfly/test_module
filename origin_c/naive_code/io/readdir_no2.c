#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>
#include <pwd.h>
#include <grp.h>
#include <time.h>
#include <locale.h>
#include <langinfo.h>
#include <stdio.h>
#include <stdint.h>

int main()
{
    DIR* dir = NULL;
    struct dirent *dp;
    struct stat statbuf;
    struct passwd *pwd;
    struct group *grp;
    struct tm *tm;
    char   date[256];

    dir = opendir("./");

    while((dp = readdir(dir)) != NULL)
    {
        if( !strcmp(dp->d_name,".") || !strcmp(dp->d_name,"..") )
            continue;
        if(stat(dp->d_name,&statbuf)<0)
            continue;
        //printf("%10.10s",sperm(statbuf.st_mode));
        printf("%06lo ",(unsigned long)statbuf.st_mode);
        printf("%4d ",statbuf.st_nlink);

        if((pwd=getpwuid(statbuf.st_uid)) != NULL)
            printf("%-8.8s ",pwd->pw_name);
        else
            printf("%-8d ",statbuf.st_uid);

        if((grp = getgrgid(statbuf.st_gid)) != NULL)
            printf("%-8.8s ",grp->gr_name);
        else
            printf("%-8d ",statbuf.st_gid);

        //printf("%lld ",(intmax_t)statbuf.st_size);
        printf("%lld ",(long long int)statbuf.st_size);

        tm = localtime(&statbuf.st_mtime);
        strftime(date,sizeof(date),nl_langinfo(D_T_FMT),tm);

        printf("%s %s \n",date,dp->d_name);

    }
}
