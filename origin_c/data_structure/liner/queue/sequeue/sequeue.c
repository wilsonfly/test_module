#include <stdio.h>
#include <stdlib.h>
#include "sequeue.h"

sequeue * sequeue_create()
{
	int i = 0;
	sequeue_p p = (sequeue *)malloc(sizeof(sequeue));
	for( i=0;i<MAX_NUM;i++ )
		p->data[i] = malloc(MAX_FILENAME_LEN);

	p->front = p->rear = 0;
	p->count = 0;
	return p;
}

int sequeue_enqueue(sequeue *sq, datatype x)
{
	if( (sq->rear+1) % MAX_NUM == sq->front )
		return -1;

	sq->rear = (sq->rear + 1) % MAX_NUM;
	strncpy(sq->data[sq->rear],x,MAX_FILENAME_LEN);

	sq->count++;
	return 0;
}

int sequeue_dequeue(sequeue *sq, datatype *x)
{
	if(sq->front == sq->rear)
		return -1;

	sq->front = (sq->front + 1) % MAX_NUM;
	
	*x = sq->data[sq->front];
	sq->count--;
	return 0;
}

int sequeue_is_empty(sequeue *sq)
{
	return (sq->front == sq->rear);
}

int sequeue_clear(sequeue *sq)
{
	sq->front = sq->rear = 0;
	sq->count = 0;
	return 0;
}

void sequeue_display(sequeue *sq)
{
	if(sq->front == sq->rear)
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

