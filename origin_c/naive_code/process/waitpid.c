#include<stdio.h>
#include<sys/wait.h>
#include<unistd.h>
#include<stdlib.h>
#include<sys/types.h>

int main()
{
	int pc,pd;
	pc = fork();
	if (pc < 0 )
	{
		printf("error fork");
	}
	else if(pc == 0)
	{
		sleep(3);
		exit(0);
	}
	else 
	{
		do
		{
			pd=waitpid(pc,NULL,WNOHANG);
			if( pd == 0)
			{
				printf("the child has not exit\n");
				sleep(1);
			}

		}while ( pd==0 );
		if(pd == pc)
		{
			printf("get child exit code:%d\n",pd);
		}
		else
		{
			printf("error occured\n");
		}
	}

}
