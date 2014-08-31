#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<errno.h>
#include<sys/types.h>
#include<string.h>
#include<semaphore.h>
#define MAX_DATA_LEN 256
#define DELAY_TIME 1

int main()
{
	int pid;
	int fd[2];
	char buf[MAX_DATA_LEN];
	const char data[] = "pipe test program";
	int nr, nw;


	memset((void*)buf,0,sizeof(buf));
	if(pipe(fd)<0)
	{
		printf("pipe create error\n");
		exit(-1);
	}
	if((pid = fork()) == 0)
	{
		close(fd[1]);
		sleep(DELAY_TIME * 3);
		if ((nr = read(fd[0],buf,MAX_DATA_LEN))>0)
		{
			printf("%d bytes read from the pipe is '%s'\n",nr,buf);
		}
		close(fd[0]);
		exit(0);
	}
	else if(pid > 0)
	{
		close(fd[0]);
		sleep(DELAY_TIME);
		if((nw=write(fd[1],data,strlen(data)))!=-1)
		{
			printf("parent wrote %d bytes: '%s'\n",nw,data);
		}
		close(fd[1]);
		wait(pid,NULL,0);
		exit(0);
	}
}
