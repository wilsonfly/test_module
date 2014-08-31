#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>

#define KEY_MSG  0x101
#define MSGSIZE 64
typedef struct
{
	long mtype;
	char mtext[MSGSIZE];
}msgbuf;
#define LEN sizeof(msgbuf)-sizeof(long)

#define TYPE_CLIENT1_TO_SERVER 1L
#define TYPE_CLIENT2_TO_SERVER 2L
#define TYPE_SERVER_TO_CLIENT1 3L
#define TYPE_SERVER_TO_CLIENT2 4L

#define MSG_QUIT "quit"
