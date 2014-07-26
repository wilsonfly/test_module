#ifndef _SEQUEUE_H_
#define _SEQUEUE_H_

#define MAX_NUM (3+1) 
typedef int datatype;

typedef struct
{	
	datatype data[MAX_NUM];
	int front;
	int rear;
}sequeue;

sequeue * sequeue_create();
int sequeue_enqueue(sequeue *sq, datatype x);
int sequeue_dequeue(sequeue *sq, datatype *x);
int sequeue_is_empty(sequeue *sq);
int sequeue_clear(sequeue *sq);
void sequeue_display(sequeue *sq);



#endif
