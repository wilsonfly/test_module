#include <stdio.h>
#include <stdlib.h>

typedef struct node
{
	int coef;
	int exp;
	struct node* next;
}listnode;

listnode * polylist_create(int n, int m, int a[][m])
{
	listnode *h = (listnode *)malloc(sizeof(listnode));
	h->next = NULL;
	int i;
	listnode *p, *q;
	p = h;
	for(i = 0; i < n; i++)
	{
		q = (listnode *)malloc(sizeof(listnode));
		q->coef = a[i][0];
		q->exp = a[i][1];
		q->next = NULL;

		p->next = q;
		p = q;
	}
	return h;
}

/*
 * y1 = ax^0 + bx^1 + cx^8 + dx^16;
 * y2 = ex^1 + fx^6 + gx^8;
 * Y = y1 + y2;
 * Y = ax^0 + (b+3)x^1 + cx^8 + fx^6 + dx^16;
 */
void polylist_add(listnode *h1, listnode *h2)
{
	if( h1==NULL || h2==NULL )
		return;

	listnode *p, *q, *r;
	p = h1->next;
	q = h2->next;
	r = h1;
	while(p && q)
	{
		printf("%d %d\n", p->exp, q->exp);
		if(p->exp < q->exp)
		{
			r->next = p;
			r = p;			
			p = p->next;
		}
		else if(p->exp > q->exp)
		{
			r->next = q;
			r = q;
			q = q->next;
		}
		else
		{
			p->coef += q->coef;
			if(p->coef)
			{
				r->next = p;
				r = p;
			}
			p = p->next;
			q = q->next;
		}
	}
	printf("************\n");

	if(p == NULL)
		r->next = q;
	else
		r->next = p;	
}

void polylist_display(listnode *h)
{
	if( h1==NULL || h2==NULL )
		return;

	listnode *p = h->next;
	while(p)
	{
		printf("(%d, %d)  ", p->coef, p->exp);
		p = p->next;
	}
	printf("\n");
}

void display(int n, int m,  int a[][m])
{
	int i, j;

	for(i = 0; i < n; i++)
	{
		for(j = 0; j < m; j++)
		{
			printf("%d ", a[i][j]);
		}
	}
	printf("\n");
}

int main()
{
	int A[][2] = {{5, 0}, {2, 1}, {8, 8}, {3, 16}};
	int B[][2] = {{6, 1}, {23, 6}, {-8, 8}};
	listnode *h1 = polylist_create(4, 2, A);
	listnode *h2 = polylist_create(3, 2, B);
	polylist_display(h1);
	polylist_display(h2);
	polylist_add(h1, h2);
	polylist_display(h1);
}
