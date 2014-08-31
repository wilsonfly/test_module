#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
long long sum;
pthread_t a_thread1,a_thread2,a_thread3,a_thread4,a_thread5;

void *fun1();
void *fun2();
void *fun3();
void *fun4();
void *fun5();
void thread_create();
pthread_mutex_t mutex;

int main()
{
	int i=0,m=0;
    thread_create();
	
	if(pthread_mutex_init(&mutex,NULL)<0)
	{
		perror("fail to mutex_init\n");
		exit(-1);
	}
    
	printf("waiting for thr thread to finish\n");
	if(pthread_join(a_thread1,NULL)<0)
	{
		perror("fail to pthread_join1\n");
		exit(-1);
	}
	else
	{
		m++;
		printf("finish a_thread1\n");
	}
	if(pthread_join(a_thread2,NULL)<0)
	{
		perror("fail to pthread_join2\n");
		exit(-1);
	}
	else
	{
		m++;
		printf("finish a_thread2\n");
	}
	if(pthread_join(a_thread3,NULL)<0)
	{
		perror("fail to pthread_join3\n");
		exit(-1);
	}
	else
	{
		m++;
		printf("finish a_thread3\n");
	}
	if(pthread_join(a_thread4,NULL)<0)
	{
		perror("fail to pthread_join4\n");
		exit(-1);
	}
	else
	{
		m++;
		printf("finish a_thread4\n");
	}
	if(pthread_join(a_thread5,NULL)<0)
	{
		perror("fail to pthread_join5\n");
		exit(-1);
	}
	else
	{
		m++;
		printf("finish a_thread5\n");
	}
   if(m == 5)	
	printf("sum:%lld\n",sum);

   return 0;
}
void thread_create()
{
	if(pthread_create(&a_thread1,NULL,fun1,NULL))
	{
		printf("creat1e error\n");
		exit(-1);
	}
	if(pthread_create(&a_thread2,NULL,fun2,NULL))
	{
		printf("create2 error\n");
		exit(-1);
	}
	if(pthread_create(&a_thread3,NULL,fun3,NULL))
	{
		printf("create3 error\n");
		exit(-1);
	}
	if(pthread_create(&a_thread4,NULL,fun4,NULL))
	{
		printf("creat4e error\n");
		exit(-1);
	}
	if(pthread_create(&a_thread5,NULL,fun5,NULL))
	{
		printf("create5 error\n");
		exit(-1);
	}
}
void *fun1()
{
	int i;
	long long s=0;
	for(i=1;i<100000000;i++)
		s+=i;
	pthread_mutex_lock(&mutex);
	sum+=s;
	pthread_mutex_unlock(&mutex);
}
void *fun2()
{
	int i;
	long long s=0;
	for(i=100000000;i<200000000;i++)
		s+=i;
	pthread_mutex_lock(&mutex);
	sum+=s;
	pthread_mutex_unlock(&mutex);
}
void *fun3()
{
	int i;
	long long s=0;
	for(i=200000000;i<300000000;i++)
		s+=i;
	pthread_mutex_lock(&mutex);
	sum+=s;
	pthread_mutex_unlock(&mutex);
}
void *fun4()
{
	int i;
	long long s=0;
	for(i=300000000;i<400000000;i++)
		s+=i;
	pthread_mutex_lock(&mutex);
	sum+=s;
	pthread_mutex_unlock(&mutex);
}
void *fun5()
{
	int i;
	long long s=0;
	for(i=400000000;i<=500000000;i++)
		s+=i;
	pthread_mutex_lock(&mutex);
	sum+=s;
	pthread_mutex_unlock(&mutex);
}
