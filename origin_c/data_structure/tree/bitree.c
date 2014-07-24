#include <stdio.h>
#include <stdlib.h>
#include "sequeue.h"
#include "bitree.h"

bitree *bitree_create(int n, int i)
{
	bitree *p = (bitree *)malloc(sizeof(bitree));
	p->data = i;
	p->left = (2 * i <= n) ? bitree_create(n, 2*i) : NULL;
	p->right = (2*i+1 <= n) ? bitree_create(n, 2*i+1) : NULL;
	return p;
}

void bitree_preorder(bitree *r)
{
	if(r == NULL)
		return;
	printf("%d  ", r->data);
	bitree_preorder(r->left);
	bitree_preorder(r->right);
}

void bitree_inorder(bitree *r)
{
	if(r == NULL)
		return;
	bitree_inorder(r->left);
	printf("%d  ", r->data);
	bitree_inorder(r->right);
}

void bitree_postorder(bitree *r)
{
	if(r == NULL)
		return;
	bitree_postorder(r->left);
	bitree_postorder(r->right);
	printf("%d  ", r->data);
}

void bitree_layerorder(bitree *r)
{
	bitree *p;
	sequeue *sq;
	sq = sequeue_create();
	printf("%d ", r->data);
	sequeue_enqueue(sq, r);
	while(!sequeue_is_empty(sq))
	{
		sequeue_dequeue(sq, &p);
		if(p->left)
		{
			printf("%d ", p->left->data);
			sequeue_enqueue(sq, p->left);
		}
		if(p->right)
		{
			printf("%d ", p->right->data);
			sequeue_enqueue(sq, p->right);
		}
	}

}

void bitree_layerorder2(bitree *r)
{
	bitree *p;
	sequeue *sq;
	sq = sequeue_create();
	sequeue_enqueue(sq, r);
	while(!sequeue_is_empty(sq))
	{
		sequeue_dequeue(sq, &p);
		printf("%d ", p->data);
		if(p->left)
			sequeue_enqueue(sq, p->left);
		if(p->right)
			sequeue_enqueue(sq, p->right);
	}

}
void bitree_layerorder3(bitree *r)
{
	bitree *a[128];
	int front, rear;
	front = 0;
	rear = 1;
	a[rear] = r;

	bitree *p;
	while(front != rear)
	{
		p = a[front+1];
		front++;
		printf("%d ", p->data);
		if(p->left)
			a[++rear] = p->left;
		if(p->right)
			a[++rear] = p->right;
	}
}
