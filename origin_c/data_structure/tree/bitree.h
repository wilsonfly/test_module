#ifndef _BITREE_H_
#define _BITREE_H_

typedef struct node
{
	int data;
	struct node *left;
	struct node *right;
}bitree;

bitree *bitree_create(int n, int i);
void bitree_preorder(bitree *r);
void bitree_inorder(bitree *r);
void bitree_postorder(bitree *r);
void bitree_layerorder(bitree *r);
void bitree_layerorder2(bitree *r);
void bitree_layerorder3(bitree *r);


#endif
