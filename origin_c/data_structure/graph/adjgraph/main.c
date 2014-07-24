#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include "adjlist.h"
#define NV 6
#define NE 8

int main()
{            
	//adjnode s[NV] = {{0, NULL}};
	adjnode *s = adjlist_create(NV);
	int c[NE][2] = {{0, 1}, {0, 3}, {0, 4}, {1, 5}, {2, 1}, {5, 2}, {4, 3}, {4, 5}};
	int id[NV] = {0};

	int i;
	for(i = 0; i < NE; i++)
	{
		adjlist_insert(s, c[i][0], c[i][1]);
	}

	adjlist_topsort(s, id, NV);


	return 0;
}
