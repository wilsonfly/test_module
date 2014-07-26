#include <stdlib.h>
#include <stdio.h>
#include "linklist.h"

int main()
{
	int data[] = {31, 23, 7, 9, 15, 20};
	int i;

	listnode *L1;
	linklist_create2(&L1);

	for(i = 0; i < sizeof(data)/sizeof(int); i++)
	{
		linklist_order_insert(L1, data[i]);
	}
	linklist_display(L1);
	linklist_order_insert(L1, 10);
	linklist_display(L1);
	if(linklist_delete_at(L1, 200) == -1)
	{
		printf("delete one num error\n");
	}
	linklist_display(L1);
	linklist_delete(L1, data[1]);
	linklist_display(L1);
	linklist_reverse(L1);
	linklist_display(L1);

	printf("************\n");
	linklist_clear(L1);
	linklist_display(L1);

	if(linklist_insert(L1, 10, 21) == -1) printf("insert error\n");
	if(linklist_insert(L1, 1, 21) == -1) printf("insert error\n");
	if(linklist_insert(L1, 0, 21) == -1) printf("insert error\n");
	linklist_display(L1);

	return 0;
}
