#include <stdio.h>
#include <stdlib.h>
#include "linkqueue.h"
#include "mgraph.h"

mgraph * mgraph_create(int n, int a[][n])
{
	int i, j;
	mgraph *p = (mgraph *)malloc(sizeof(mgraph));
	for(i = 0; i < n; i++)
		p->vertex[i] = i;

	for(i = 0; i < n; i++)
	{
		for(j = 0; j < n; j++)
			p->edge[i][j] = a[i][j];
	}
	p->n = n;
	return p;
}

int mgraph_firstadj(mgraph *mg, int v)
{
	int i;
	for(i = 0; i < mg->n; i++)
	{
		if(mg->edge[v][i] == 1)
			return i;
	}
	return -1;
}

int mgraph_nextadj(mgraph *mg, int v, int u)
{
	int i;
	for(i = u+1; i < mg->n; i++)
	{
		if(mg->edge[v][i] == 1)
			return i;
	}
	return -1;
}

void mgraph_DFS(mgraph *mg, int v, int *visited)
{
	int u;
	printf("V%d ", v);
	visited[v] = 1;

	u = mgraph_firstadj(mg, v);
	while(u >= 0)
	{
		if(!visited[u])
		{
			mgraph_DFS(mg, u, visited);
		}
		u = mgraph_nextadj(mg, v, u);
	}
}

void mgraph_BFS(mgraph *mg, int v, int *visited)
{
	int u;
	linkqueue *lq;
	lq = linkqueue_create();

	printf("V%d ", v);
	visited[v] = 1;
	linkqueue_enqueue(lq, v);
	while(!linkqueue_is_empty(lq))
	{
		linkqueue_dequeue(lq, &v);
		u = mgraph_firstadj(mg, v);
		while( u >= 0)
		{
			if(!visited[u])
			{
				printf("V%d ", u);
				visited[u] = 1;
				linkqueue_enqueue(lq, u);
			}
			u = mgraph_nextadj(mg, v, u);
		}
	}

}
