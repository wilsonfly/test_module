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

	linkqueue_display(lq);
	while(!linkqueue_is_empty(lq))
	{
		linkqueue_dequeue(lq, &t);
		printf("dequeue:%d \n", t);
	}
	
	linkqueue_display(lq);
	linkqueue_enqueue(lq, 41);
	linkqueue_display(lq);
	while(!linkqueue_is_empty(lq))
	{
		linkqueue_dequeue(lq, &t);
		printf("dequeue:%d \n", t);
	}
	linkqueue_display(lq);

	linkqueue_destroy(lq);

	return 0;
}
