#include <stdio.h>
#include <stdlib.h>
#include "seqstack.h"

seqstack * seqstack_create()
{
	seqstack * p = (seqstack *)malloc(sizeof(seqstack));
	p->top = -1;
	return p;
}

void seqstack_create2(seqstack **sq)
{
	*sq = (seqstack *)malloc(sizeof(seqstack));
	(*sq)->top = -1;
}

int seqstack_push(seqstack *sq, type x)
{
	if(sq->top == maxsize - 1)
		return -1;
	sq->data[++sq->top] = x;
	return 0;
}

int seqstack_pop(seqstack *sq, type *x)
{
	if(sq->top == -1)
		return -1;
	*x = sq->data[sq->top--];
	return 0;
}	


int seqstack_top(seqstack *sq, type *x)
{
	if(sq->top == -1)
		return -1;
	*x = sq->data[sq->top];
	return 0;

}

int seqstack_is_empty(seqstack *sq)
{
	return (sq->top == -1);
}

void seqstack_clear(seqstack *sq)
{
	sq->top = -1;
}

void seqstack_display(seqstack *sq)
{
	int i;
	for(i = 0; i <= sq->top; i++)
		printf("%d ", sq->data[i]);
	printf("\n");
}

