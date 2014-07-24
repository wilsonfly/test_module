#ifndef _LINKLIST_H_
#define _LINKLIST_H_

typedef int datatype;

typedef struct node
{
	datatype data;
	struct node *next;
}listnode;

listnode *linklist_create();
void linklist_create2(listnode **L);
int linklist_insert(listnode *L, int i, datatype x);
void linklist_order_insert(listnode *L, datatype x);
int linklist_delete_at(listnode *L, int i);
void linklist_delete(listnode *L, datatype x);
int linklist_is_empty(listnode *L);
int linklist_clear(listnode *L);
void linklist_display(listnode *L);
void linklist_reverse(listnode *L);

#endif
