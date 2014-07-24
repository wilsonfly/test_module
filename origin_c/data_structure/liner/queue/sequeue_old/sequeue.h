#ifndef _SEQUEUE_H_
#define _SEQUEUE_H_

#define maxsize 3 
typedef int datatype;

typedef struct
{	
	datatype data[maxsize];
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
