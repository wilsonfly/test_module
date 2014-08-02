#include <stdio.h>
#include <stdlib.h>

#define JOSEPHUS_NUM 8
typedef struct list
{
	int data;
	struct list* next;
}josephus_list;

josephus_list* josephus_create(int num);
void josephus_display(josephus_list* L);
void josephus_run(josephus_list* L, int begin_index, int step_length);


int main()
{
	josephus_list* L = NULL;
	int begin_index = 1, step_length = 1;
	//int begin_index = 1, step_length = 2;
	//int begin_index = 1, step_length = JOSEPHUS_NUM-1;
	//int begin_index = 1, step_length = JOSEPHUS_NUM;
	//int begin_index = 1, step_length = JOSEPHUS_NUM+1;
	//int begin_index = 2, step_length = 1;
	//int begin_index = 2, step_length = 2;
	//int begin_index = 2, step_length = JOSEPHUS_NUM-1;
	//int begin_index = 2, step_length = JOSEPHUS_NUM;
	//int begin_index = 2, step_length = JOSEPHUS_NUM+1;

	L = josephus_create(JOSEPHUS_NUM);
	josephus_display(L);
	josephus_run(L, begin_index, step_length);
	printf("At last:");
	josephus_display(L);
}

/*
 *@brief with one head_node
 */
josephus_list* josephus_create(int num)
{
	josephus_list *p = NULL, *q=NULL, *L=NULL;
	int i = 1;

	L = (josephus_list*)malloc(sizeof(josephus_list));
	L->data = 0;
	L->next = L;

	p = L;
	for( i=1; i<=num; i++)
	{
		q = (josephus_list*)malloc(sizeof(josephus_list));
		q->data = i;
		q->next = L;
		p->next = q;
		p = q;
	}

	return L;
}

void josephus_display(josephus_list* L)
{
	if( L==NULL || L->next==L )
	{
		printf("empty list\n");
		return;
	}
	josephus_list *p = L->next;
	while( p != L)
	{
		printf("%d ", p->data);
		p = p->next;
	}
	printf("\n");
}

/*
 * @param begin_index, count from this,[1-~)
 * @param step_length, step these time everytime,[1-~)
 */
void josephus_run(josephus_list* L, int begin_index, int step_length)
{

	if( L==NULL || L->next==L )
	{
		printf("empty list\n");
		return;
	}
	int i = 1;
	josephus_list *p = L, *q = NULL;
	while(i<begin_index)
	{
		p = p->next;
		++i;
		if( p->next == L )
			p = p->next;
	}

	printf("The point before begin_index:%d \n", p->data);

	while( !(p->next==L && L->next==p) && !(p->next->next==L && L==p) )//1.L!=p,p is the last one 2. L==p, p->next is tht last one
	{
		if(p->next->next == L)
			printf("p->next->next == L\n");
		if(L->next == p)
			printf("L->next == p\n");
		if(p->next == L)
			printf("p->next == L\n");

		i = 1;
		while( i<step_length )
		{
			p = p->next;
			++i;
		}

		if( p->next == L )
			p = p->next;

		q = p->next;
		p->next = q->next;
		printf("free %d\n", q->data);
		free(q);
	}
}

