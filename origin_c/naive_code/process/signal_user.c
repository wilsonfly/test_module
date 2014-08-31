#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>
void lntDelete();
void lnt1();
void lnt2();
int pid1,pid2;
int endflag=0;
//int pf1=0;
//int pf2=0;

int main()
{
	int exitcode;
	//	signal(SIGINT,SIG_IGN);
	signal(SIGQUIT,SIG_IGN);
	if( (pid1=fork()) == -1 )
		printf("fork error\n");
	if(pid1 == 0)
	{
		signal(SIGUSR1,lnt1);
		signal(SIGINT,SIG_IGN);
		pause();
		exit(0);
	}
	else
	{
		if((pid2=fork())==-1)
			printf("fork error\n");
		if(pid2==0)
		{
			signal(SIGUSR2,lnt2);
			signal(SIGINT,SIG_IGN);
			pause();
			exit(0);
		}
		else
		{
			signal(SIGINT,lntDelete);
			waitpid(-1,&exitcode,0);
			printf("parent process exit\n");
			/**the exitcode of child process**/
			printf("the exitcode:%d\n",exitcode);
			exit(0);
		}
	}
	return 0;
}
void lntDelete()
{
	//kill(pid1,10);
	kill(pid1,SIGUSR1);
	sleep(1);
	//kill(pid2,12);
	kill(pid2,SIGUSR2);
	endflag=1;
}
void lnt1()
{
	printf("\nchild process 1 is killed by parent\n");
	exit(0);
}
void lnt2()
{
	printf("child process 2 is killed by parent\n");
	exit(0);
}
