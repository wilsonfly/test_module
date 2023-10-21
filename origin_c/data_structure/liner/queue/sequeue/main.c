#include <stdio.h>
#include <stdlib.h>
#include "sequeue.h"

int main()
{
	char one[] = "one";
	char two[] = "two";
	char three[] = "three";
	char four[] = "four";
	char five[] = "five";
	char *tmp;
	sequeue_p sq;

	sq = sequeue_create();
	if(sequeue_enqueue(sq, one)) printf("NO.1 enqueue error\n");
	else printf("%d member left\n",sq->count);
	sequeue_display(sq);

	if(sequeue_enqueue(sq, two)) printf("NO.2 enqueue error\n");
	else printf("%d member left\n",sq->count);

	if(sequeue_enqueue(sq, three)) printf("NO.3 enqueue error\n");
	else printf("%d member left\n",sq->count);

	if(sequeue_enqueue(sq, four)) printf("NO.4 enqueue error\n");
	else printf("%d member left\n",sq->count);

	if(sequeue_enqueue(sq, five)) printf("NO.5 enqueue error\n");
	else printf("%d member left\n",sq->count);

	if(sequeue_enqueue(sq, five)) printf("NO.6 enqueue error\n");
	else printf("%d member left\n",sq->count);

	sequeue_dequeue(sq, &tmp);

	if(sequeue_enqueue(sq, five)) printf("NO.7 enqueue error\n");
	else printf("%d member left\n",sq->count);
	
	sequeue_dequeue(sq, &tmp);
	
	if(sequeue_enqueue(sq, five)) printf("NO.7 enqueue error\n");
	else printf("%d member left\n",sq->count);
	
	sequeue_display(sq);
	while(!sequeue_is_empty(sq))
	{
		sequeue_dequeue(sq, &tmp);
		printf("%s \n", tmp);
	}
	sequeue_display(sq);
	
	sequeue_destroy(sq);
	return 0;
}
