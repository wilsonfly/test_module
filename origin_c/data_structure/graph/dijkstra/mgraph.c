#include <stdio.h>
#include <stdlib.h>
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

void mgraph_dijkstra(mgraph *mg, int v, int *s, int *dist, pathtype *path)
{
	int i, n, u, count = 1, m = 1024;
	n = mg->n;
	s[v] = 1;
	for(i = 0; i < n; i++)
	{
		dist[i] = mg->edge[v][i];
		path[i].end = 0;
		path[i].pi[0] = 0;
	}

	while(count <= n-1)
	{
		m = 1024;
		for(i = 0; i < n; i++)
		{
			if(s[i] == 0 && dist[i] < m)
			{
				u = i;
				m = dist[i];
			}
		}
		s[u] = 1;
		count++;
		path[u].end++;
		path[u].pi[path[u].end] = u;

		for(i = 0; i < n; i++)
		{
			if(s[i] == 0 && dist[i] > dist[u] + mg->edge[u][i])
			{
				dist[i] = dist[u] + mg->edge[u][i];
				path[i] = path[u];
				/*
				 * path[i].end = path[u].end;
				 * for(i = 0; i <= path[u].end; i++)
				 * 	path[i].pi[i] = path[u].pi[i];
				 * */
			}
		}
	}
}
