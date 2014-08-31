#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <semaphore.h>
char buf[60];
sem_t sem;
void *fun(void *arg);
int main()
{
	pthread_t a_thread;
	void  *thread_result;

	if(sem_init(&sem,0,0)<0)
	{
		perror("fail to sem_init\n");
		exit (-1);
	}
	/*** after init,call  fun(),fun() is blocked***/
	if(pthread_create(&a_thread,NULL,fun,NULL)<0)
	{
		perror("fail to pthread_create\n");
		exit(-1);
	}
	printf("input 'quit' to exit\n");
	do
	{
		fgets(buf,60,stdin);
		sem_post(&sem);
	}
	while(strncmp(buf,"quit",4)!=0);

	return 0;
}

void *fun(void *arg)
{
	while(1)
	{
		sem_wait(&sem);
		printf("you enter %d charactes\n",strlen(buf)-1);
	}
}
