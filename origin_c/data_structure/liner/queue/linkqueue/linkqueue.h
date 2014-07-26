#ifndef _LINKQUEUE_H_
#define _LINKQUEUE_H_

typedef int datatype;

typedef struct node
{
	datatype data;
	struct node *next;
}listnode;

typedef struct
{
	listnode *front;
	listnode *rear;
}linkqueue;

linkqueue *linkqueue_create();
int linkqueue_destroy(linkqueue* lq);
int linkqueue_enqueue(linkqueue *lq, datatype x);
int linkqueue_dequeue(linkqueue *lq, datatype *x);
int linkqueue_is_empty(linkqueue *lq);
void linkqueue_display(linkqueue* lq);

#endif
