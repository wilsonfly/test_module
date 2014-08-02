#include <stdio.h>
#include <stdlib.h>
#include "seqstack.h"

int main()
{
	int a[] = {1, 3, 5, 7, 9}, i, t;
	seqstack *sq;
	seqstack_create2(&sq);

	for(i = 0;i < 5; i++)
		seqstack_push(sq, a[i]);

	seqstack_display(sq);

	while(!seqstack_is_empty(sq))
	{
		seqstack_pop(sq, &t);
		printf("%d ", t);
	}
	printf("\n");

	return 0;
}

