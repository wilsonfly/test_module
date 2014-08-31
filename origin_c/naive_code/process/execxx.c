#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
int main()
{
	char* argv[]={"ls","-i",NULL};
	char* argv2[]={"ls","-a",NULL};
	char * envp[]={"PATH=/home/linux/sheng/process","USER=linux",NULL};
	pid_t pid; 
	pid_t pid2;
	if((pid=fork()) == -1)
	{
		perror("fork error");
		exit(-1);
	}
	if(pid==0)
	{	
		printf("ls -l from execl:\n");
		execl("/bin/ls","ls","-l",NULL);
		printf("\n");
	}

	if(pid>0)
	{
		if((pid2=fork()) == -1)
		{
			perror("fork error");
			exit(-1);
		}
		if(pid2==0)
		{
			printf("ls -i from exeve:\n");
			execve("/bin/ls",argv,envp);
			printf("\n");
		}

		if(pid2>0)
		{
			printf("ls -a from execvp:\n");
			execvp("ls",argv2);
			printf("\n");
		}
	}

	/*
	   char *const argv[]={"ps","-ef",NULL};
	   execv("/bin/ps",argv);
	   */
	//	char * envp[]={"PATH=/home/linux/sheng/process","USER=linux",NULL};
	//	execle("/bin/ps","-ef",NULL,envp);

	//   execve("/bin/ps",argv,envp);  
	//X    execve("/home/linux/sheng/process/a.out",argv,envp);  
	return 0;
}
