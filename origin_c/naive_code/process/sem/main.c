#include <stdio.h>
#include "shm.h"
int main()
{
	int shmid,semid;
	char *shmaddr;
	key_t key;
	key=ftok(".",'a');
	
	shmid=shm_get(key,&semid);
    
	sem_init(semid,1);
//	printf("sem_p ing\n");
	sem_p(semid);
	
	shmaddr=shm_at(shmid);
  	shm_dt(shmaddr);
    
	sem_v(semid);

   	shm_ctl(shmid);

	return 0;
}
