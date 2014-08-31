#include <stdio.h>
#include <sys/types.h>
#include <errno.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <limits.h>
#include <string.h>
#define MYFIFO "./myfifo"
#define BUFSIZE 256

int main(int argc,char **argv)
{
	int fd;
	char buf[BUFSIZE];
	int num;

	if(argc<2)
	{
		printf("use:%s myfifo\n",argv[0]);
		exit(1);
	}
	fd=open(MYFIFO,O_WRONLY);
	if(fd==-1)
	{
		printf("open fifo error\n");
		exit(1);
	}
	printf("input quit to stop\n");
	while(1)	
	{
		printf("please input the message:");
		scanf("%s",buf);
		if((num=write(fd,buf,BUFSIZE))>0)
			printf("write '%s' to fifo\n",buf);
		if(strncmp(buf,"quit",4)==0)
			break;
		memset(&buf,0,sizeof(buf));
	}
	close(fd);
	return 0;
}

