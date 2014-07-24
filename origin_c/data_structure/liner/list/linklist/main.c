#include <stdlib.h>
#include <stdio.h>
#include "linklist.h"

int main()
{
	int a[] = {31, 23, 7, 9};
	int i;

	listnode *L1;
	linklist_create2(&L1);

	for(i = 0; i < 4; i++)
	{
		linklist_order_insert(L1, a[i]);
	}
	linklist_display(L1);
	linklist_order_insert(L1, 100);
	linklist_display(L1);
	if(linklist_delete_at(L1, 200) == -1)
	{
		printf("delete ope error\n");
	}
	linklist_display(L1);
	linklist_delete(L1, 100);
	linklist_display(L1);
	linklist_reverse(L1);
	linklist_display(L1);

	printf("************\n");
//	linklist_clear(L1);
	/*
	linklist_display(L1);

	if(linklist_insert(L1, 10, 21) == -1)
		printf("insert error\n");
	linklist_display(L1);
*/

	return 0;
}
