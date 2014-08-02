#include <stdio.h>
#include <stdlib.h>
#include "seqstack.h"

seqstack * seqstack_create()
{
	seqstack * p = (seqstack *)malloc(sizeof(seqstack));
	p->top_index = -1;
	return p;
}

void seqstack_create2(seqstack **sq)
{
	*sq = (seqstack *)malloc(sizeof(seqstack));
	(*sq)->top_index = -1;
}

int seqstack_push(seqstack *sq, datatype x)
{
	if( sq==NULL || sq->top_index==maxsize-1 )
		return -1;
	sq->data[++sq->top_index] = x;
	return 0;
}

int seqstack_pop(seqstack *sq, datatype *x)
{
	if( sq==NULL || x==NULL || sq->top_index==-1)
		return -1;
	*x = sq->data[sq->top_index--];
	return 0;
}	


int seqstack_top(seqstack *sq, datatype *x)
{
	if( sq==NULL || x==NULL || sq->top_index == -1)
		return -1;
	*x = sq->data[sq->top_index];
	return 0;

}

int seqstack_is_empty(seqstack *sq)
{
	if( sq == NULL )
		return -1;
	return (sq->top_index == -1);
}

void seqstack_clear(seqstack *sq)
{
	if( sq == NULL )
		return ;
	sq->top_index = -1;
}

void seqstack_display(seqstack *sq)
{
	if( sq == NULL )
		return ;

	int i;
	for(i = 0; i <= sq->top_index; i++)
		printf("%d ", sq->data[i]);
	printf("\n");
}

