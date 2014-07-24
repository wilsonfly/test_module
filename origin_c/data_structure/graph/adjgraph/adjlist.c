#include <stdio.h>
#include <stdlib.h>
#include "linkstack.h"
#include "adjlist.h"

adjnode * adjlist_create(int n)
{
	adjnode *p = (adjnode *)malloc(n * sizeof(adjnode));
	p->data = 0;
	p->next = NULL;

	return p;
}
/*
 * note: acsending order
 * */
void adjlist_insert(adjnode *s, int n, int m)
{
	adjnode *q;
	adjnode *p = (adjnode *)malloc(sizeof(adjnode));
	p->data = m;
	p->next = NULL;

	//s[n]
	q = &s[n];
	while(q->next && m > q->next->data)
	{
		q = q->next;
	}
	
	p->next = q->next;
	q->next = p;
}

int adjlist_firstadj(adjnode *s, int v)
{
	adjnode *p = &s[v];
	//s[v]
	if(s[v].next == NULL)
		return -1;
	return s[v].next->data;
}

int adjlist_nextadj(adjnode *s, int v, int u)
{
	adjnode *p = s[v].next;
	while(p->data != u)
		p = p->next;
	if(p->next == NULL)
		return -1;
	return p->next->data;
}

void adjlist_DFS(adjnode *s, int v, int *visited)
{
	int u;
	printf("V%d ", v);
	visited[v] = 1;
	u = adjlist_firstadj(s, v);
	while(u >= 0)
	{
		if(!visited[u])
			adjlist_DFS(s, u, visited);
		u = adjlist_nextadj(s, v, u);
	}

}

void adjlist_BFS(adjnode *s, int v, int *visited)
{
	int a[128];
	int front, rear, u;
	front = rear = 0;

	printf("V%d ", v);
	visited[v] = 1;
	a[++rear] = v;

	while(front != rear)
	{
		v = a[front+1];
		front++;

		u = adjlist_firstadj(s, v);
		while(u >= 0)
		{
			if(!visited[u])
			{
				printf("V%d ", u);
				visited[u] = 1;
				a[++rear] = u;
			}
			u = adjlist_nextadj(s, v, u);
		}
	}
}

void adjlist_topsort(adjnode *s, int *id, int n)
{
	listnode *ls;
	adjnode *p;
	ls = linkstack_create();
	int i, u, v;
	for(i = 0; i < n; i++)
	{
		p = s[i].next;
		while(p)
		{
			id[p->data]++;
			p = p->next;
		}
	}
	for(i = 0; i < n; i++)
	{
		printf("id[%d] = %d \n", i, id[i]);
		if(id[i] == 0)
			linkstack_push(ls, i);
	}

	while(!linkstack_is_empty(ls))
	{
		linkstack_pop(ls, &v);
		printf("V%d ", v);
		p = s[v].next;
		while(p)
		{
			id[p->data]--;
			if(id[p->data] == 0)
				linkstack_push(ls, p->data);
			p = p->next;
		}
	}
	printf("\n");
}
