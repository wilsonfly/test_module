#include "shm.h"

int shm_get(key_t key)
{
	int id;
	if((id=shmget(key,BUFSZ,0666|IPC_CREAT))<0)
		//	if((shmid=shmget(IPC_PRIVATE,BUFSZ,0666))<0)
	{
		perror("shmget error");
		exit(-1);
	}
	printf("created shared_memory:%d\n",id);
	system("ipcs -m");
	return id;	
}
char *shm_at(int shmid)
{
	char *addr;
	if((addr=(char *)shmat(shmid,NULL,0))<(char *)0)
	{
		perror("shmat error\n");
		exit(-1);
	}
	printf("attacheded shared_memory:\n");
	system("ipcs -m");
	return addr;	
}
void shm_dt(char *addr)
{
	if((shmdt(addr))<0)
	{
		perror("shmdt error\n");
		exit(-1);
	}
	printf("detach shared_memory:\n");
	system("ipcs -m");
}
void shm_ctl(int shmid)
{
	shmctl(shmid,IPC_RMID,NULL);
	printf("after control\n");
	system("ipcs -m");
}
