#include <stdio.h>
#include <stdlib.h>
#include "linkstack.h"

int main()
{
	int data[] = {1, 3, 5, 7, 9};
	int i, t;
	listnode *lq;
	linkstack_create2(&lq);

	for(i = 0;i < sizeof(data)/sizeof(int); i++)
		linkstack_push(lq, data[i]);

	linkstack_display(lq);

	while(!linkstack_is_empty(lq))
	{
		linkstack_pop(lq, &t);
		printf("pop:%d ", t);
	}
	printf("\n");

	linkstack_display(lq);

	return 0;
}

