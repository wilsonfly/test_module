#include <stdio.h>
#include <stdlib.h>
#include "sequeue.h"

sequeue * sequeue_create()
{
	sequeue *sq = (sequeue *)malloc(sizeof(sequeue));
	sq->front = sq->rear = 0;
	return sq;
}

int sequeue_destroy(sequeue* sq)
{
	if( sq==NULL )
		return -1;
	free(sq);
	return 0;
}

int sequeue_enqueue(sequeue *sq, datatype x)
{
	if( sq==NULL || (sq->rear+1)%MAX_NUM==sq->front )
		return -1;

	sq->rear = (sq->rear + 1) % MAX_NUM;
	sq->data[sq->rear] = x;

	return 0;
}

int sequeue_dequeue(sequeue *sq, datatype *x)
{
	if( sq==NULL || sq->front==sq->rear )
		return -1;

	sq->front = (sq->front + 1) % MAX_NUM;
	
	*x = sq->data[sq->front];
	
	return 0;
}

int sequeue_is_empty(sequeue *sq)
{
	if( sq==NULL )
		return -1;
	return (sq->front == sq->rear);
}

int sequeue_clear(sequeue *sq)
{
	if( sq==NULL )
		return -1;
	sq->front = sq->rear;
	return 0;
}

void sequeue_display(sequeue *sq)
{
	if( sq==NULL || sq->front==sq->rear )
	{
		printf("empty queue\n");
		return;
	}
	int i = (sq->front + 1) % MAX_NUM;
	while(i != sq->rear)
	{
		printf("%d ", sq->data[i]);
		i = (i + 1) % MAX_NUM;
	}
	printf("%d\n", sq->data[sq->rear]);
}

