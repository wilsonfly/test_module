#ifndef _SEQUEUE_H_
#define _SEQUEUE_H_

#include <string.h>

#define MAX_NUM (5+1) 
#define MAX_FILENAME_LEN 32

typedef char* datatype;

//sequence queue
typedef struct
{	
	datatype data[MAX_NUM];
	int front;
	int rear;
	int count;
}sequeue,*sequeue_p;

sequeue * sequeue_create();
int sequeue_destroy(sequeue* sq);
int sequeue_enqueue(sequeue *sq, datatype x);
int sequeue_dequeue(sequeue *sq, datatype *x);
int sequeue_is_empty(sequeue *sq);
int sequeue_clear(sequeue *sq);
void sequeue_display(sequeue *sq);



#endif
