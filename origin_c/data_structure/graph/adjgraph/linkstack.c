#include <stdio.h>
#include <stdlib.h>
#include "linkstack.h"

listnode* linkstack_create()
{
	listnode *p = (listnode *)malloc(sizeof(listnode));
	p->next = NULL;
	return p;
}

void linkstack_create2(listnode **lq)
{
	*lq = (listnode *)malloc(sizeof(listnode));
	(*lq)->next = NULL;
}

int linkstack_push(listnode *lq, datatype x)
{
	listnode *p = lq, *q;

	q = (listnode *)malloc(sizeof(listnode));
	q->data = x;

	q->next = p->next;
	p->next = q;
	return 0;
}

int linkstack_pop(listnode *lq, datatype *x)
{
	listnode *p = lq, *q;
	if(p->next == NULL)
		return -1;
	q = p->next;
	p->next = q->next;
	*x = q->data;
	free(q);
	q = NULL;

	return 0;
}

int linkstack_top(listnode *lq, datatype *x)
{
	if(lq->next == NULL)
		return -1;
	*x = lq->next->data;

	return 0;
}

int linkstack_is_empty(listnode *lq)
{
	return (lq->next == NULL);
}

void linkstack_clear(listnode *lq)
{
	listnode *p = lq, *q;
	while(p->next)
	{
		q = p->next;
		p->next = q->next;
		printf("%d ", q->data);
		free(q);
	}
	printf("\n");
}

void linkstack_display(listnode *lq)
{
	listnode *p = lq->next;
	while(p)
	{
		printf("%d ", p->data);
		p = p->next;
	}
	printf("\n");
}

