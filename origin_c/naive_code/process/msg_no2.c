#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdlib.h>
#include <string.h>

#define BUFSZ 512
#define TYPE 100
#define IPCKEY 0x123

#if 1
struct msgbuf
{
	long mtype;
	char mtext[BUFSZ];
};
#endif

int main()
{
	int qid,len;
	key_t key;
	struct msgbuf msg;
	struct msgbuf msg2;
	pid_t pid;
	if( (pid=fork()) > 0)
	{
		//if((key=ftok(".",'a'))==-1)
		if((key=ftok("/tmp",IPCKEY))==-1)
		{
			perror("ftok error\n");
			exit(1);
		}
		if((qid = msgget(key,IPC_CREAT|0666))==-1)
		{
			perror("msgget error\n");
			exit(-1);
		}
		printf("parent process,opened queue %d\n",qid);
		puts("please enter the message to queue:");
		if((fgets((&msg)->mtext,BUFSZ,stdin)) == NULL)
		{
			puts("no message\n");
			exit(1);
		}
		msg.mtype=TYPE;
		len=strlen(msg.mtext)+1;
		if(msgsnd(qid,&msg,len,0)<0)
		{
			perror("msgsnd error\n");
			exit(1);
		}
	}
	else
	{
		//if((key=ftok(".",'a'))==-1)
		if((key=ftok("/tmp",IPCKEY))==-1)
		{
			perror("ftok error\n");
			exit(1);
		}
		if((qid = msgget(key,IPC_CREAT|0666))==-1)
		{
			perror("msgget error\n");
			exit(-1);
		}
		msg2.mtype=TYPE;
		printf("child process,opened queue %d\n",qid);
		if(msgrcv(qid,&msg2,BUFSZ,0,0)<0)
		{
			perror("msgrcv error\n");
			exit(1);
		}
		printf("message is :%s",(&msg2)->mtext);
	}
	return 0;
}
