#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
char message[32]="hello world";
void *thread_fun(void *arg);

int main()
{
	pthread_t a_thread;
	void *thread_result;
	if(pthread_create(&a_thread,NULL,thread_fun,(void *)message)<0)
	{
		perror("fail to pthread_create\n");
		exit(-1);
	}
	printf("waiting for thread to finish\n");
	if(pthread_join(a_thread,&thread_result)<0)
	{
		perror("fail to pthread_join\n");
		exit(-1);
	}
	printf("message is now %s\n",message);
	
	return 0;
}

void *thread_fun(void *arg)
{
	printf("thread_fun is running,argument is %s\n",(char *)arg);
	strcpy(message,"marked by thread");
	pthread_exit("thank you for the cpu time");
}
