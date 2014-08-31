#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>

//#define _LOCK_

int value1,value2;
pthread_mutex_t mutex;
void *fun(void *arg);
unsigned int count=0;

int main()
{
	pthread_t a_thread;
	void *thread_result;
	if(pthread_create(&a_thread,NULL,fun,NULL)<0)
	{
		perror("fail to pthread_create\n");
		exit(-1);
	}
	if(pthread_mutex_init(&mutex,NULL)<0)
	{
		perror("fail to mutex_init\n");
		exit(-1);
	}
	while(1)
	{
		count++;
#ifdef _LOCK_
		pthread_mutex_lock(&mutex);
#endif
		value1=count;
		value2=count;
#ifdef _LOCK_
		pthread_mutex_unlock(&mutex);
#endif
	}

	return 0;
}

void *fun(void *arg)
{
	while(1)
	{
		if(value1!=value2)
		{
			printf("count=%d,value1=%d,value2=%d\n",count,value1,value2);
		}
	}
	return NULL;
}
