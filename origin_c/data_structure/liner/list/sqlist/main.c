#include <stdio.h>
#include <stdlib.h>
#include "sqlist.h"

int main()
{
	int a[] = {1, 3, 5, 7}, i;
	int b[] = {1, 23, 7, 37, 2};
	sqlist *L1, *L2;
	L1 = sqlist_create();
	L2 = sqlist_create();

	for(i = 0; i < 4; i++)
	{
		if(sqlist_insert(L1, 0, a[i]) == -1)
		{
			printf("insert error!\n");
		}
	}

	for(i = 0; i < sizeof(b)/sizeof(int); i++)
	{
		if(sqlist_insert(L2, 0, b[i]) == -1)
		{
			printf("insert error!\n");
		}
	}
	sqlist_display(L1);
	sqlist_display(L2);
	sqlist_union(L1, L2);
	sqlist_display(L1);
	/*
	sqlist_insert(L1, 2, 8);
	sqlist_display(L1);

	sqlist_delete(L1, 0);
	sqlist_delete(L1, L1->last);

	sqlist_display(L1);
*/
	return 0;
}
