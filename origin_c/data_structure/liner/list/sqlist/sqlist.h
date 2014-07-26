#ifndef _SQLIST_H_
#define _SQLIST_H_

#define MAX_NUM 128
typedef int datatype;

typedef struct 
{
	datatype data[MAX_NUM];
	int last_index;
}sqlist;

sqlist *sqlist_create();
void sqlist_set_empty(sqlist *L);
int sqlist_is_empty(sqlist *L);
int sqlist_length(sqlist *L);
int sqlist_get(sqlist *L, int i, datatype *x);
int sqlist_locate(sqlist *L, datatype x);
int sqlist_insert(sqlist *L, int i, datatype x);
int sqlist_delete(sqlist *L, int i);
void sqlist_display(sqlist *L);
void sqlist_union(sqlist *L1, sqlist *L2);


#endif
