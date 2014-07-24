#ifndef _MGRAPH_H_
#define _MGRAPH_H_
#define maxsize 128

typedef struct
{
	int vertex[maxsize];
	int edge[maxsize][maxsize];
	int n;
}mgraph;

typedef struct
{
	int pi[maxsize];
	int end;
}pathtype;


mgraph * mgraph_create(int n, int a[][n]);
void mgraph_dijkstra(mgraph *mg, int v, int *s, int *dist, pathtype *path);
#endif
