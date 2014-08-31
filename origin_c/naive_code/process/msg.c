#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdlib.h>
#include <string.h>
#define BUFSZ 512
#define TYPE 100
struct msgbuf
{
	long mtype;
	char mtext[BUFSZ];
};
int main()
{
	int qid,len;
	key_t key;
	struct msgbuf msg;
//	struct msgbuf msg2;
	if((key=ftok(".",'a'))==-1)
	{
		perror("ftok error\n");
		exit(1);
	}
	if((qid = msgget(key,IPC_CREAT|0666))==-1)
	{
		perror("msgget error\n");
		exit(-1);
	}
	printf("opened queue %d\n",qid);
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
	if(msgrcv(qid,&msg,BUFSZ,0,0)<0)
	{
		perror("msgrcv error\n");
		exit(1);
	}
	printf("message is :%s",(&msg)->mtext);
	return 0;
}
