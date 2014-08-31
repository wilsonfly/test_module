#include "chat.h"
#include <pthread.h>

static void* fun_client1(void* arg);
static void* fun_client2(void* arg);

int exit_flag = 0;
int main()
{
	int msgid;
	msgbuf buf1,buf2;
	pthread_t pid1, pid2;

	msgid=msgget(KEY_MSG,IPC_CREAT|0666);

	pthread_create(&pid1, NULL, fun_client1, &msgid);
	pthread_create(&pid2, NULL, fun_client2, &msgid);

	//pthread_join(pid1,NULL);
	//pthread_join(pid2,NULL);
	while( !exit_flag )
		sleep(1);
	msgctl(msgid,IPC_RMID,NULL);
	return 0;
}

static void* fun_client1(void* arg)
{
	int msgid = *(int*)arg;
	msgbuf buf1;

	while( !exit_flag)
	{
		msgrcv(msgid,&buf1,LEN,TYPE_CLIENT1_TO_SERVER,0);
		printf("receive client1 message:%s\n",buf1.mtext);
		if( !strncmp(buf1.mtext, MSG_QUIT, strlen(MSG_QUIT)) )
		{
			strcpy(buf1.mtext,MSG_QUIT);

			buf1.mtype=TYPE_SERVER_TO_CLIENT1;
			msgsnd(msgid,&buf1,LEN,0);

			buf1.mtype=TYPE_SERVER_TO_CLIENT2;
			msgsnd(msgid,&buf1,LEN,0);
			exit_flag = 1;
			break;
		}
		buf1.mtype=TYPE_SERVER_TO_CLIENT2;
		msgsnd(msgid,&buf1,LEN,0);
	}
}

static void* fun_client2(void* arg)
{
	int msgid = *(int*)arg;
	msgbuf buf2;

	while( !exit_flag )
	{
		msgrcv(msgid,&buf2,LEN,TYPE_CLIENT2_TO_SERVER,0);
		printf("receive client2 message:%s\n",buf2.mtext);
		if( !strncmp(buf2.mtext, MSG_QUIT, strlen(MSG_QUIT)) )
		{
			strcpy(buf2.mtext,MSG_QUIT);

			buf2.mtype=TYPE_SERVER_TO_CLIENT1;
			msgsnd(msgid,&buf2,LEN,0);

			buf2.mtype=TYPE_SERVER_TO_CLIENT2;
			msgsnd(msgid,&buf2,LEN,0);
			exit_flag = 1;
			break;
		}
		buf2.mtype=TYPE_SERVER_TO_CLIENT1;
		msgsnd(msgid,&buf2,LEN,0);
	}
}
