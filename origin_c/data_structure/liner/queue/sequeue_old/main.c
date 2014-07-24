#include <stdio.h>
#include <stdlib.h>
#include "sequeue.h"

int main()
{
	int t;
	sequeue *sq;
	sq = sequeue_create();
	if(sequeue_enqueue(sq, 1) == -1)
		printf("NO.1 enqueue error\n");
	if(sequeue_enqueue(sq, 3) == -1)
		printf("NO.2 enqueue error\n");
	if(sequeue_enqueue(sq, 5) == -1)
		printf("NO.3 enqueue error\n");
	
	sequeue_display(sq);
	while(!sequeue_is_empty(sq))
	{
		sequeue_dequeue(sq, &t);
		printf("%d \n", t);
	}
	sequeue_display(sq);
	

	return 0;
}
