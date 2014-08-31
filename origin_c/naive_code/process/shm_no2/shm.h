
#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdlib.h>
#define BUFSZ 2048

int shm_get(key_t key);
char *shm_at(int shmid);
void shm_dt(char *addr);
void shm_ctl(int shmid);
