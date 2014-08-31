
#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdlib.h>
#include <sys/sem.h>
#define BUFSZ 2048

int shm_get(key_t key,int *semid);
char *shm_at(int shmid);
void shm_dt();
void shm_ctl();
int sem_init(int semid,int value);
int sem_p(int semid);
int sem_v(int semid);
