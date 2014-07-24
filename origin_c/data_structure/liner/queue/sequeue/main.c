#include <stdio.h>
#include <stdlib.h>
#include "sequeue.h"

int main()
{
	char one[] = "one";
	char two[] = "two";
	char three[] = "three";
	char *tmp;
	sequeue_p sq;

	sq = sequeue_create();
	if(sequeue_enqueue(sq, one) )
		printf("NO.1 enqueue error\n");
	else
		printf("%d member left\n",sq->count);
	if(sequeue_enqueue(sq, two))
		printf("NO.2 enqueue error\n");
	else
		printf("%d member left\n",sq->count);
	if(sequeue_enqueue(sq, three) )
		printf("NO.3 enqueue error\n");
	else
		printf("%d member left\n",sq->count);
	
	sequeue_display(sq);
	printf("display over\n");
	while(!sequeue_is_empty(sq))
	{
		sequeue_dequeue(sq, &tmp);
		printf("%s \n", tmp);
	}
	sequeue_display(sq);
	
	return 0;
}
