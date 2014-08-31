#include <stdio.h>
#include "shm.h"
int main()
{
	int shmid;
	char *shmadd;
	key_t key;
	key=ftok(".",'a');
	
	shmid=shm_get(key);
	
	shmadd=shm_at(shmid);
    
	shm_dt(shmadd);
   
   	shm_ctl(shmid);

	return 0;
}
