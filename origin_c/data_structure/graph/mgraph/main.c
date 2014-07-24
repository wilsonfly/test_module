#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include "mgraph.h"

#define N 5
int main()
{            
               	/* v0 v1 v2 v3 v4*/
	int a[][5] = {
		  /*v0*/   {0, 1, 1, 1, 0},
		  /*v1*/   {1, 0, 1, 0, 1},
		  /*v2*/   {1, 1, 0, 1, 0},
		  /*v3*/   {1, 0, 1, 0, 1},
		  /*v4*/   {0, 1, 0, 1, 0}	};

               	/* v0 v1 v2 v3 v4 v5 v6 v7 v8*/
	int b[][9] = {
		  /*v0*/   {0, 1, 1, 0, 0, 0, 0, 0, 0},
		  /*v1*/   {0, 0, 0, 1, 0, 1, 0, 0, 0},
		  /*v2*/   {0, 0, 0, 0, 0, 0, 1, 1, 0},
		  /*v3*/   {0, 1, 0, 0, 1, 0, 0, 0, 0},
		  /*v4*/   {0, 0, 0, 0, 0, 1, 0, 0, 1},
		  /*v5*/   {0, 1, 0, 0, 1, 0, 0, 0, 0},
		  /*v6*/   {0, 0, 1, 0, 0, 0, 0, 1, 0},
		  /*v7*/   {0, 0, 1, 0, 0, 0, 1, 0, 0},
		  /*v8*/   {0, 0, 0, 0, 1, 0, 0, 0, 0}};

	int visited[9] = {0};

	//mgraph *mg = mgraph_create(5, a);
	mgraph *mg = mgraph_create(9, b);
	mgraph_DFS(mg, 0, visited);
	printf("\n");

	bzero(visited, sizeof(visited));
	mgraph_BFS(mg, 0, visited);

	printf("\n");

	return 0;
}
