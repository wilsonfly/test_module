#ifndef _SEQSTACK_H_
#define _SEQSTACK_H_

#define maxsize 128 
typedef int type;

typedef struct
{
	type data[maxsize];
	int top;
}seqstack;

seqstack * seqstack_create();
void seqstack_create2(seqstack **sq);
int seqstack_push(seqstack *sq, type x);
int seqstack_pop(seqstack *sq, type *x);
int seqstack_top(seqstack *sq, type *x);
int seqstack_is_empty(seqstack *sq);
void seqstack_clear(seqstack *sq);
void seqstack_display(seqstack *sq);

#endif
