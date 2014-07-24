#include <stdio.h>
#include <stdlib.h>
#include "linkstack.h"

int main()
{
	int a[] = {1, 3, 5, 7, 9}, i, t;
	listnode *lq;
	linkstack_create2(&lq);

	for(i = 0;i < 5; i++)
		linkstack_push(lq, a[i]);

	//linkstack_display(lq);

	while(!linkstack_is_empty(lq))
	{
		linkstack_pop(lq, &t);
		printf("%d ", t);
	}
	printf("\n");

	return 0;
}

