#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/wait.h>
#include "llog_priv.h"

int main()
{
	pid_t pid;
	int ret;
	if((pid = fork()) < 0)
	{
		perror("fork");
		exit(1);
	}
	if(pid == 0)
	{
		log("child process exit\n");
//		raise(SIGSTOP);
		log("child process exit\n");
		exit(0);
	}
	else
	{
		log("pid = %d\n",pid);
		if((waitpid(pid,NULL,WNOHANG)) == 0)
		{
			kill(pid ,SIGKILL);
			log("kill %d\n",pid);
		}
		exit(0);
	}
}
