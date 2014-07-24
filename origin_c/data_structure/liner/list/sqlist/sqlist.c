#include <stdio.h>
#include <stdlib.h>
#include "sqlist.h"

/*
 * sqlist-create-create an empty sqlist
 * return: the start address of the sqlist
 * */
sqlist *sqlist_create()
{
	sqlist *p = (sqlist *)malloc(sizeof(sqlist));
	p->last = -1;
	return p;
}

void sqlist_set_empty(sqlist *L)
{
	L->last = -1;
}

/*
 *return: 1 emtpy
 * */
int sqlist_is_empty(sqlist *L)
{
	return (L->last == -1);
}

int sqlist_length(sqlist *L)
{
	return (L->last+1);
}

/*
 * note: i at [0, last]
 * */
int sqlist_get(sqlist *L, int i, datatype *x)
{
	if(i < 0 || i > L->last)
		return -1;
	 *x = L->data[i];
	 return 0;
}

/*
 * return: -1 not exist
 * */
int sqlist_locate(sqlist *L, datatype x)
{
	int i = 0;
	while(i <= L->last)
	{
		if(x == L->data[i])
			return i;
		i++;
	}
	return -1;
}

/*
 * sqlist_insert-insert x at i
 * @i-the insert pos
 * note: i [0,last+1]
 * return: -1 error 0 success
 * */
int sqlist_insert(sqlist *L, int i, datatype x)
{
	if(L->last == maxsize - 1)
		return -1;
	if(i < 0 || i > L->last+1)
		return -1;
	int j = L->last;
	while(j >= i)
	{
		L->data[j+1] = L->data[j];
		j--;
	}

	L->data[i] = x;
	L->last++;

	return 0;
}

/*
 * note: i at [0, last]
 * */
int sqlist_delete(sqlist *L, int i)
{
	if(i < 0 || i > L->last)
		return -1;

	int j = i+1;
	while(j <= L->last)
	{
		L->data[j-1] = L->data[j];
		j++;
	}
	L->last--;	

	return 0;
}

void sqlist_display(sqlist *L)
{
	int i;
	for(i = 0; i <= L->last; i++)
		printf("%d ", L->data[i]);
	printf("\n");
}

void sqlist_union(sqlist *L1, sqlist *L2)
{
	int i = 0;
	while(i <= L2->last)
	{	
		/*L2->data[i] */
		if( sqlist_locate(L1, L2->data[i]) == -1 )
		{
			sqlist_insert(L1, L1->last+1, L2->data[i]);
		}
		i++;
	}
}
