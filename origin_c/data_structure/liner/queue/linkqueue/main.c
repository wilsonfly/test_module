#include <stdio.h>
#include <stdlib.h>
#include "linkqueue.h"

int main()
{
	int t;
	linkqueue *lq;
	lq = linkqueue_create();
	linkqueue_enqueue(lq, 11);
	linkqueue_enqueue(lq, 21);
	linkqueue_enqueue(lq, 31);
	linkqueue_enqueue(lq, 41);

	while(!linkqueue_is_empty(lq))
	{
		linkqueue_dequeue(lq, &t);
		printf("%d \n", t);
	}
	
	linkqueue_enqueue(lq, 41);
	while(!linkqueue_is_empty(lq))
	{
		linkqueue_dequeue(lq, &t);
		printf("%d \n", t);
	}

	return 0;
}
