#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include "mgraph.h"

#define N 6
#define M 1 << 20

int main()
{            
               	/* v0  v1  v2 v3 v4 v5*/
	int c[N][N] = {
		  /*v0*/   {0, 20, 15, M, M, M},
		  /*v1*/   {2, 0, 4, M, 10, 30},
		  /*v2*/   {M, M, 0, M, M, 10},
		  /*v3*/   {M, M, M, 0, M, M},
		  /*v4*/   {M, M, M, 15, 0, 10},
		  /*v5*/   {M, M, M, 4, M, 0}	};

	int s[N] = {0};
	int dist[N], i, j;
	pathtype path[N];

	mgraph *mg = mgraph_create(N, c);
	mgraph_dijkstra(mg, 0, s, dist, path);

	for(i = 0; i < N; i++)
	{
		printf("(V0, V%d) L = %d 		path: ", i, dist[i]);
		for(j = 0; j <= path[i].end; j++)
			printf("V%d ", path[i].pi[j]);
		printf("\n");
	}




	printf("\n");


	return 0;
}
