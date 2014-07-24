#include <stdio.h>
#include <stdlib.h>
#include "linkqueue.h"

linkqueue *linkqueue_create()
{
	linkqueue *p = (linkqueue *)malloc(sizeof(linkqueue));
	p->front = p->rear = (listnode *)malloc(sizeof(listnode));
	p->front->next = NULL;
	return p;
}

int linkqueue_enqueue(linkqueue *lq, datatype x)
{
	listnode *p = (listnode *)malloc(sizeof(listnode));
	p->data = x;
	p->next = NULL;

	lq->rear->next = p;
	lq->rear = p;
	return 0;
}

int linkqueue_dequeue(linkqueue *lq, datatype *x)
{
	if(lq->front->next == NULL)
		return -1;
	listnode *p, *q;
	p = lq->front;
	q = p->next;

	p->next = q->next;
	*x = q->data;
	free(q);
	q = NULL;
	if(lq->front->next == NULL)
		lq->rear = lq->front;

	return 0;
}

int linkqueue_is_empty(linkqueue *lq)
{
	return (lq->front->next == NULL);
}

