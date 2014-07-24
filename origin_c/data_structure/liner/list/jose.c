#include <stdio.h>
#include <stdlib.h>

typedef struct node
{
	int data;
	struct node *next;
}listnode;

listnode * list_create(int n)
{
	listnode *L, *p, *q;

	L = (listnode *)malloc(sizeof(listnode));
	L->data = 1;
	L->next = L;
	p = L;

	int i = 2;
	while(i <= n)
	{
		q = (listnode *)malloc(sizeof(listnode));
		q->data = i;
		p->next = q;
		p = q;
		q->next = L;
		i++;
	}
	return L;
}


void list_display(listnode *L)
{
	listnode *p;
	p = L;
	while(p->next != L)
	{
		printf("%d ", p->data);
		p = p->next;
	}
	printf("%d\n", p->data);
}

void jose(listnode *L, int k, int m)
{
	listnode *p = L, *q;
	int i = 1;
	while(i < k)
	{
		p = p->next;
		i++;
	}
	printf("%d\n", p->data);

	while(p != p->next)
	{
		i = 2;
		
		while(i < m)
		{
			p = p->next;
			i++;
		}
		q = p->next;
		p->next = q->next;
		printf("%d\n", q->data);
		free(q);
		q = NULL;
		p = p->next;
	}
}

int main()
{
	listnode *L;
	L = list_create(8);
//	list_display(L);
	jose(L, 3, 4);
//	list_display(L);


	return 0;
}
