#ifndef _MGRAPH_H_
#define _MGRAPH_H_
#define maxsize 128

typedef struct
{
	int vertex[maxsize];
	int edge[maxsize][maxsize];
	int n;
}mgraph;
mgraph * mgraph_create(int n, int a[][n]);
int mgraph_firstadj(mgraph *mg, int v);
int mgraph_nextadj(mgraph *mg, int v, int u);
void mgraph_DFS(mgraph *mg, int v, int *visited);
void mgraph_BFS(mgraph *mg, int v, int *visited);

#endif
