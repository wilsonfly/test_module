#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

void fun(pid_t );
int main()
{
	pid_t pid;

	printf("parent process pid:%d is running\n",getpid());
	pid=fork();
	if( pid==0 )
		printf("child process pid:%d is running\n",getpid());
	fun(pid);
	while(1)
	{
		fprintf(stdout,"pid:%d is running\n",getpid());
		sleep(2);
	}
	return 0;
}

void fun(pid_t pid)
{
	int x,i;
	if(pid>0)
		exit(0);

	setsid();

	x=chdir("/");
	if(x<0)
		printf("chdir error\n");

	umask(0);

	//for(i=0;i<1024;i++)//should be like this,close all the files
	for(i=3;i<1024;i++)//try to use stdout in this test
		close(i);
}
