#include <stdio.h>
#include <stdlib.h>
#include "bitree.h"

int main()
{
	bitree *r;
	r = bitree_create(8, 1);
	bitree_preorder(r);
	printf("\n");
	bitree_inorder(r);
	printf("\n");
	bitree_postorder(r);
	printf("\n");
	bitree_layerorder3(r);
	printf("\n");


	return 0;
}
