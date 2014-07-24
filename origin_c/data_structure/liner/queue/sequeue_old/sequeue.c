#include <stdio.h>
#include <stdlib.h>
#include "sequeue.h"

sequeue * sequeue_create()
{
	sequeue *p = (sequeue *)malloc(sizeof(sequeue));
	p->front = p->rear = 0;
	return p;
}

int sequeue_enqueue(sequeue *sq, datatype x)
{
	if( (sq->rear+1) % maxsize == sq->front )
		return -1;

	sq->rear = (sq->rear + 1) % maxsize;
	sq->data[sq->rear] = x;

	return 0;
}

int sequeue_dequeue(sequeue *sq, datatype *x)
{
	if(sq->front == sq->rear)
		return -1;

	sq->front = (sq->front + 1) % maxsize;
	
	*x = sq->data[sq->front];
	
	return 0;
}

int sequeue_is_empty(sequeue *sq)
{
	return (sq->front == sq->rear);
}

int sequeue_clear(sequeue *sq)
{
	sq->front = sq->rear;
	return 0;
}

void sequeue_display(sequeue *sq)
{
	if(sq->front == sq->rear)
		return;
	int i = (sq->front + 1) % maxsize;
	while(i != sq->rear)
	{
		printf("%d ", sq->data[i]);
		i = (i + 1) % maxsize;
	}
	printf("%d\n", sq->data[sq->rear]);
}

