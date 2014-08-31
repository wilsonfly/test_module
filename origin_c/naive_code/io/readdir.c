#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stddef.h>
#include <dirent.h>
#include <time.h>
#include <langinfo.h>

int main()
{
	DIR *dp;
	struct dirent *ep;
	struct stat st;
	struct tm* tm;
	char dirp[50];
	char date[50];
	int x;

	dp=opendir("./");
	if(dp!=NULL)
	{
		while(ep=readdir(dp))
		{
			if(ep->d_name[0]!='.')
			{
				if(stat(ep->d_name,&st)!=-1)
				{

					switch (st.st_mode & S_IFMT) {
					case S_IFBLK:  printf("b");        break;
					case S_IFCHR:  printf("c");        break;
					case S_IFDIR:  printf("d");        break;
					case S_IFIFO:  printf("f");        break;
					case S_IFLNK:  printf("l");        break;
					case S_IFREG:  printf("-");        break;
					case S_IFSOCK: printf("s");        break;
					//default:       printf("unknown?\n");                break;
					}
					//x=st.st_mode&0x1ff;
					//x=x/64;
					x=(st.st_mode>>6)&0x7;
					switch(x)
					{
					case 7:printf("rwx");break;
					case 6:printf("rw-");break;
					case 5:printf("r-x");break;
					case 4:printf("r--");break;
					case 3:printf("-wx");break;
					case 2:printf("-x-");break;
					case 1:printf("--x");break;
					case 0:printf("---");break;
					}

					x=(st.st_mode>>3)&0x7;
					switch(x)
					{
					case 7:printf("rwx");break;
					case 6:printf("rw-");break;
					case 5:printf("r-x");break;
					case 4:printf("r--");break;
					case 3:printf("-wx");break;
					case 2:printf("-x-");break;
					case 1:printf("--x");break;
					case 0:printf("---");break;
					}

					x=st.st_mode&0x7;
					switch(x)
					{
					case 7:printf("rwx");break;
					case 6:printf("rw-");break;
					case 5:printf("r-x");break;
					case 4:printf("r--");break;
					case 3:printf("-wx");break;
					case 2:printf("-x-");break;
					case 1:printf("--x");break;
					case 0:printf("---");break;
					}
					printf("\t");


					printf("%06o  ",st.st_mode);
					printf("%03o  ",st.st_mode&0x1ff);//notice this,9 bits for the rwx permission
					printf("%2d  ",st.st_nlink);
					printf("uid:%d  ",st.st_uid);
					printf("gid:%d  ",st.st_gid);
					printf("%5ld  ",st.st_size);
					//printf("atime:%15s",ctime(&st.st_atime));
					//printf("%15s  ",ctime(&st.st_mtime));

					tm = localtime(&st.st_mtime);
					strftime(date,sizeof(date),nl_langinfo(D_T_FMT),tm);
					printf("%s %s \n",date,ep->d_name);

					//printf("%15s  ",ep->d_name);

					/*
					switch (st.st_mode & S_IFMT) {
					case S_IFBLK:  printf("block device\n");            break;
					case S_IFCHR:  printf("character device\n");        break;
					case S_IFDIR:  printf("directory\n");               break;
					case S_IFIFO:  printf("FIFO/pipe\n");               break;
					case S_IFLNK:  printf("symlink\n");                 break;
					case S_IFREG:  printf("regular file\n");            break;
					case S_IFSOCK: printf("socket\n");                  break;
					default:       printf("unknown?\n");                break;
					}
					*/
				}
			}
		}
		closedir(dp);
	}
	else
		puts("couldn't openthe directory\n");
	return 0;
}
