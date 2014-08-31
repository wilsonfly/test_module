#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main()
{
	int pid1,pid2;
	int fd1[2],fd2[2];
	char outpipe1[100],inpipe1[100];
	char outpipe2[100],inpipe2[100];

	pipe(fd1);
	pipe(fd2);

	if((pid1=fork()) == 0)//child
	{
		close(fd1[0]);
		close(fd2[1]);
		printf("child is to writing\n");

		lockf(fd1[1],1,0);
		sprintf(outpipe1,"child process is sending message!");
		write(fd1[1],outpipe1,50);
		lockf(fd1[1],0,0);

		sleep(1);
		read(fd2[0],inpipe2,50);
		printf("read from parent:%s\n",inpipe2);
		exit(0);
	}
	else//father
	{
		close(fd2[0]);
		close(fd1[1]);
		printf("parent is to writing\n");

		//lockf(fd2[1],1,0);
		sprintf(outpipe1,"parent process is sending message!");
		write(fd2[1],outpipe1,50);
		//lockf(fd2[1],0,0);

		sleep(1);
		read(fd1[0],inpipe1,50);
		printf("read from child:%s\n",inpipe1);
		exit(0);
	}

	return 0;
}
