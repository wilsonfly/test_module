#include "chat.h"

static void* fun_rcv(void* arg);
static void* fun_snd(void* arg);

int exit_flag = 0;

int main()
{
	int msgid;
	msgbuf buf1,buf2;
	msgid=msgget(KEY_MSG,0666);
	pthread_t pid1, pid2;
	pthread_create(&pid1, NULL, fun_rcv, &msgid);
	pthread_create(&pid2, NULL, fun_snd, &msgid);

	pthread_join(pid1, NULL);
	//pthread_join(pid2, NULL);
	return 0;
}

void* fun_rcv(void* arg)
{
	int msgid = *(int*)arg;
	msgbuf buf2;

	while( !exit_flag )
	{
		msgrcv(msgid,&buf2,LEN,TYPE_SERVER_TO_CLIENT2,0);
		if( !strncmp(buf2.mtext, MSG_QUIT, strlen(MSG_QUIT)) )
		{
			printf("client2 will quit!\n");
			exit_flag = 1;
			break;
		}
		printf("receive from client1,message:%s\n",buf2.mtext);
	}
}

static void* fun_snd(void* arg)
{
	int msgid = *(int*)arg;
	msgbuf buf1;

	while( !exit_flag )
	{
		printf("input the msg to client1:");
		fgets(buf1.mtext, MSGSIZE, stdin);
		if( !strcmp(buf1.mtext, "\n") )
			continue;
		buf1.mtype=TYPE_CLIENT2_TO_SERVER;
		msgsnd(msgid,&buf1,LEN,0);
	}
}
