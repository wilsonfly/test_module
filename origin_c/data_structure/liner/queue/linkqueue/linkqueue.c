#include <stdio.h>
#include <stdlib.h>
#include "linkqueue.h"

linkqueue *linkqueue_create()
{
	linkqueue *lq = (linkqueue *)malloc(sizeof(linkqueue));
	lq->front = lq->rear = (listnode *)malloc(sizeof(listnode));
	lq->front->next = NULL;
	return lq;
}

int linkqueue_destroy(linkqueue* lq)
{
	if( lq==NULL )
		return -1;
	listnode* p = lq->front;
	listnode* q = NULL;
	while( p!=NULL && p->next!=NULL )
	{
		q = p->next;
		p->next = p->next->next;
		free(q);
	}
	free(lq->front);
}

int linkqueue_enqueue(linkqueue *lq, datatype x)
{
	if( lq == NULL )
		return -1;
	listnode *p = (listnode *)malloc(sizeof(listnode));
	p->data = x;
	p->next = NULL;

	lq->rear->next = p;
	lq->rear = p;
	return 0;
}

int linkqueue_dequeue(linkqueue *lq, datatype *x)
{
	if( lq==NULL || lq->front->next==NULL)
		return -1;
	listnode *p, *q;
	p = lq->front;
	q = p->next;

	*x = q->data;
	p->next = q->next;
	free(q);
	q = NULL;
	if(lq->front->next == NULL)
		lq->rear = lq->front;

	return 0;
}

int linkqueue_is_empty(linkqueue *lq)
{
	if( lq==NULL )
		return -1;
	return (lq->front->next == NULL);
}

void linkqueue_display(linkqueue* lq)
{
	if( lq==NULL || lq->front==lq->rear )
	{
		printf("empty queue\n");
		return;
	}
	listnode* p = lq->front;
	while( p!=NULL && p->next!=NULL )
	{
		printf("%d ", p->next->data);
		p = p->next;
	}
	printf("\n");
	return ;
}

