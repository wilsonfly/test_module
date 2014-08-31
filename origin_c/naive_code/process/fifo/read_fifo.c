#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <errno.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <limits.h>
#define MYFIFO "./myfifo"
#define BUFSIZE 256

int main(int argc,char **argv)
{
	int fd;
	char buf[BUFSIZE];
	int num;
	if((mkfifo(MYFIFO,0644))<0&&(errno!=EEXIST))
	{
		perror("create fifo error");
		exit(1);
	}
	fd=open(MYFIFO,O_RDONLY);
	if(fd<0)
	{
		printf("open fifo error\n");
		exit(1);
	}
	while(1)
	{
		memset(&buf,0,sizeof(buf));
		if((num=read(fd,buf,BUFSIZE))>0)
		{
			if(strncmp(buf,"quit",4))
				printf("read '%s' from fifo\n",buf);	
			else
				break;
		}
	}

	close(fd);
	return 0;
}

