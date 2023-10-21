#include <stdio.h>
#include <stdlib.h>
#include "bitree.h"

/*
gcc main.c bitree.c sequeue.c
./a.out
 */

/*
	r = bitree_create(8, 1);
1  2  4  8  5  3  6  7
8  4  2  5  1  6  3  7
8  4  5  2  6  7  3  1
1 2 3 4 5 6 7 8
 */

/*
	r = bitree_create(7, 1);
1  2  4  5  3  6  7
4  2  5  1  6  3  7
4  5  2  6  7  3  1
1 2 3 4 5 6 7

 */
int main()
{
	bitree *r;
	r = bitree_create(7, 1);
	bitree_preorder(r);
	printf("\n");
	bitree_inorder(r);
	printf("\n");
	bitree_postorder(r);
	printf("\n");

	bitree_layerorder(r);
	printf("\n");
	bitree_layerorder2(r);
	printf("\n");
	bitree_layerorder3(r);
	printf("\n");
	bitree_layerorder4(r);
	printf("\n");

	return 0;
}
