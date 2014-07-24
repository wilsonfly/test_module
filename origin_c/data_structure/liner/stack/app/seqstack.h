#ifndef _SEQSTACK_H_
#define _SEQSTACK_H_

#define maxsize 5
typedef int datatype;

typedef struct
{
	datatype data[maxsize];
	int top;
}seqstack;

seqstack * seqstack_create();
void seqstack_create2(seqstack **sq);
int seqstack_push(seqstack *sq, datatype x);
int seqstack_pop(seqstack *sq, datatype *x);
int seqstack_top(seqstack *sq, datatype *x);
int seqstack_is_empty(seqstack *sq);
void seqstack_clear(seqstack *sq);
void seqstack_display(seqstack *sq);

#endif
