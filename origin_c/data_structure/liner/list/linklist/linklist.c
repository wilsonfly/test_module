#include <stdio.h>
#include <stdlib.h>
#include "linklist.h"

listnode *linklist_create()
{
	listnode *p = (listnode *)malloc(sizeof(listnode));//head node,not put data in it;
	p->next = NULL;
	return p;
}

void linklist_create2(listnode **L)
{
	*L = (listnode *)malloc(sizeof(listnode));
	(*L)->next = NULL;	
}

int linklist_insert(listnode *L, int i, datatype x)
{
	if( L==NULL )
		return -1;
	listnode *p, *q;
	p = L;
	int j = 0;
	/*p point to the pre pos*/
	while(j < i && p != NULL)
	{
		p = p->next;
		j++;
	}
	if(p != NULL)
	{
		q = (listnode *)malloc(sizeof(listnode));
		q->data = x;

		q->next = p->next;
		p->next = q;
		return 0;
	}
	else
		return -1;
}

void linklist_order_insert(listnode *L, datatype x)
{
	if( L==NULL )
		return ;
	listnode *p = L, *q;
	while(p->next && p->next->data < x)
	{
		p = p->next;
	}
	q = (listnode *)malloc(sizeof(listnode));
	q->data = x;

	q->next = p->next;
	p->next = q;
}

int linklist_delete_at(listnode *L, int i)
{
	if( L==NULL )
		return -1;
	listnode *p = L;
	listnode *q;
	int j = 0;
	/*p point to the pre pos*/
	while(j < i && p != NULL)
	{
		p = p->next;
		j++;
	}
	if(p != NULL)
	{
		 q = p->next;
		 p->next = q->next;
		 free(q);
		 q = NULL;
		 return 0;
	}
	else
		return -1;
}

void linklist_delete(listnode *L, datatype x)
{
	if( L==NULL )
		return ;
	listnode *p = L, *q;
	while(p->next && p->next->data != x)
		p = p->next;
	if(p->next != NULL)
	{
		q = p->next;
		p->next = q->next;
		free(q);
		q = NULL;
	}
}

int linklist_is_empty(listnode *L)
{
	if( L==NULL )
		return -1;
	return (L->next == NULL);
}

int linklist_clear(listnode *L)
{
	if( L==NULL )
		return -1;
	listnode *p = L, *q;
	while(p->next)
	{
		q = p->next;
		p->next = q->next;
		printf("%d ", q->data);
		free(q);
		q = NULL;
	}
	L->next = NULL;
	printf(" clear finished!\n");
}

void linklist_display(listnode *L)
{
	if( L==NULL )
	{
		printf("empty list\n");
		return;
	}
	listnode *p = L->next;//no data in head node.
	while(p)
	{
		printf("%d ", p->data);
		p = p->next;
	}
	printf("\n");
}

/*
 *@brief reverse the linklist
 * Should be noteced !
 */
void linklist_reverse(listnode *L)
{
	if(L->next == NULL || L->next->next == NULL)
		return;
	listnode *p, *q, *r;
	p = L->next->next;
	q = L->next;
	r = NULL;

	while(p)
	{
		q->next = r;
		r = q;
		q = p;
		p = p->next;
	}

	q->next = r;
	/*
	r = q;
	L->next = r;
	*/
	L->next = q;//same as last two lines
}
