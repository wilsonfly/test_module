#ifndef _LINKSTACK_H_
#define _LINKSTACK_H_

typedef int datatype;

typedef struct node
{
	datatype data;
	struct node *next;
}listnode;

listnode* linkstack_create();
void linkstack_create2(listnode **sq);
int linkstack_push(listnode *sq, datatype x);
int linkstack_pop(listnode *sq, datatype *x);
int linkstack_top(listnode *sq, datatype *x);
int linkstack_is_empty(listnode *sq);
void linkstack_clear(listnode *sq);
void linkstack_display(listnode *sq);

#endif
