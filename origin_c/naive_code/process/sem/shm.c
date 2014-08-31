#include "shm.h"

int shm_get(key_t key,int *semid)
{
	int shmid;
	if((shmid=shmget(key,BUFSZ,0666|IPC_CREAT))<0)
		//	if((shmid=shmget(IPC_PRIVATE,BUFSZ,0666))<0)
	{
		perror("shmget error");
		exit(-1);
	}
	printf("created shared_memory:%d\n",shmid);
	system("ipcs -m");

	*semid=semget(key,1,0600|IPC_CREAT);

	return shmid;
}
int sem_init(int semid,int init_value)
{/*/////////////////////////
   struct ipc_perm
   {
   key_t key;

   };
   struct semid_ds
   {
   struct ipc_perm sem_perm;
   time_t sem_otime;
   time_t sem_ctime;
   unsigned short sem_nsems;
   };
	///////////////////  */
	union semun
	{
		int val;
		struct semid_ds s_ds;
		unsigned short *array;
	};
	union semun sem_union;
	sem_union.val=init_value;
	struct semid_ds *buf;
	unsigned short *array;
	if((semctl(semid,0,SETVAL,sem_union))==-1)
	{
		perror("seminit error");
		return -1;
	}
	return 0;
}
int sem_p(int semid)
{
	struct sembuf sem_b;
	sem_b.sem_num=0;
	sem_b.sem_op=-1;
	sem_b.sem_flg=SEM_UNDO;
	if(semop(semid,&sem_b,1)==-1)
	{
		perror("p operation error");
		return -1;
	}
	printf("sem_p succeed\n");
	return 0;
}
int sem_v(int semid)
{
	struct sembuf sem_b;
	sem_b.sem_num=0;
	sem_b.sem_op=1;
	sem_b.sem_flg=SEM_UNDO;
	if(semop(semid,&sem_b,1)==-1)
	{
		perror("p operation error");
		return -1;
	}
	return 0;
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
	//	system("ipcs -m");
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
	//	system("ipcs -m");
}
void shm_ctl(int shmid)
{
	shmctl(shmid,IPC_RMID,NULL);
	printf("after control\n");
	//	system("ipcs -m");
}
