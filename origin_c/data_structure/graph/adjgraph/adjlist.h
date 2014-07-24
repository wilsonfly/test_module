#ifndef _ADJLIST_H_
#define _ADJLIST_H_

typedef struct _node_
{
	int data;
	struct _node_ *next;
}adjnode;

adjnode* adjlist_create(int n);
void adjlist_insert(adjnode *s, int n, int m);
int adjlist_firstadj(adjnode *s, int v);
int adjlist_nextadj(adjnode *s, int v, int u);
void adjlist_DFS(adjnode *s, int v, int *visited);
void adjlist_BFS(adjnode *s, int v, int *visited);
void adjlist_topsort(adjnode *s, int *id, int n);



#endif
