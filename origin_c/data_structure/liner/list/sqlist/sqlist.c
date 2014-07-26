#include <stdio.h>
#include <stdlib.h>
#include "sqlist.h"

sqlist *sqlist_create()
{
	sqlist *sl = (sqlist *)malloc(sizeof(sqlist));
	sl->last_index = -1;
	return sl;
}

void sqlist_set_empty(sqlist *sl)
{
	if( sl==NULL )
		return;
	sl->last_index = -1;
}

int sqlist_is_empty(sqlist *sl)
{
	if( sl==NULL )
		return -1;
	return (sl->last_index == -1);
}

int sqlist_length(sqlist *sl)
{
	if( sl==NULL )
		return 0;
	return (sl->last_index+1);
}

int sqlist_get(sqlist *sl, int i, datatype *x)
{
	if( sl==NULL || i<0 || i>sl->last_index )
		return -1;
	 *x = sl->data[i];
	 return 0;
}

/*
 *@brief check if x is in sl
 *@return exist:index; nonexist:-1
 **/
int sqlist_locate(sqlist *sl, datatype x)
{
	if( sl==NULL )
		return -1;
	int i = 0;
	while(i <= sl->last_index)
	{
		if(x == sl->data[i])
			return i;
		i++;
	}
	return -1;
}

int sqlist_insert(sqlist *sl, int i, datatype x)
{
	if( sl==NULL || sl->last_index==MAX_NUM-1 )
		return -1;
	if(i < 0 || i > sl->last_index+1)
		return -1;

	int j = sl->last_index;
	while(j >= i)
	{
		sl->data[j+1] = sl->data[j];
		j--;
	}

	sl->data[i] = x;
	sl->last_index++;

	return 0;
}

/*
 *@brief delete the one at index
 */
int sqlist_delete(sqlist *sl, int index)
{
	if( sl==NULL || index<0 || index>sl->last_index )
		return -1;

	int j = index+1;
	while(j <= sl->last_index)
	{
		sl->data[j-1] = sl->data[j];
		j++;
	}
	sl->last_index--;	

	return 0;
}

void sqlist_display(sqlist *sl)
{
	int i;
	for(i = 0; i <= sl->last_index; i++)
		printf("%d ", sl->data[i]);
	printf("\n");
}

/*
 *@brief insert sl2 into sl1 without the duplicated ones
 */
void sqlist_union(sqlist *sl1, sqlist *sl2)
{
	int i = 0;
	while(i <= sl2->last_index)
	{	
		/*sl2->data[i] */
		if( sqlist_locate(sl1, sl2->data[i]) == -1 )
		{
			sqlist_insert(sl1, sl1->last_index+1, sl2->data[i]);
		}
		i++;
	}
}
