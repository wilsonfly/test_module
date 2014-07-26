#include <stdio.h>
#include <stdlib.h>
#include "sequeue.h"

sequeue * sequeue_create()
{
	int i = 0;
	sequeue_p sq = (sequeue *)malloc(sizeof(sequeue));
	for( i=0;i<MAX_NUM;i++ )
		sq->data[i] = (datatype)malloc(MAX_FILENAME_LEN);

	sq->front = sq->rear = 0;
	sq->count = 0;
	return sq;
}

int sequeue_destroy(sequeue* sq)
{
	if( sq==NULL )
		return -1;

	int i = 0;
	for( i=0;i<MAX_NUM;i++ )
		free(sq->data[i]);

	free(sq);
	return 0;
}

int sequeue_enqueue(sequeue *sq, datatype x)
{
	if( sq==NULL || (sq->rear+1)%MAX_NUM==sq->front )
		return -1;

	sq->rear = (sq->rear + 1) % MAX_NUM;
	strncpy(sq->data[sq->rear],x,MAX_FILENAME_LEN);
	//printf("front:%d, rear:%d\n", sq->front, sq->rear);

	sq->count++;
	return 0;
}

int sequeue_dequeue(sequeue *sq, datatype *x)
{
	if( sq==NULL || sq->front==sq->rear )
		return -1;

	sq->front = (sq->front + 1) % MAX_NUM;
	
	*x = sq->data[sq->front];
	sq->count--;
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

	sq->front = sq->rear = 0;
	sq->count = 0;
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
		printf("%s\n", sq->data[i]);
		i = (i + 1) % MAX_NUM;
	}
	printf("%s\n", sq->data[sq->rear]);
	printf("%d members in the queue\n",sq->count);
}

