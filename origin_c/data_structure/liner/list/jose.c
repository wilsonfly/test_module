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
void josephus_run(josephus_list** L, int begin_index, int step_length);


int main()
{
	josephus_list* L = NULL;
	//int begin_index = 1, step_length = 1;
	//int begin_index = 1, step_length = 2;
	//int begin_index = 1, step_length = JOSEPHUS_NUM-1;
	//int begin_index = 1, step_length = JOSEPHUS_NUM;
	//int begin_index = 1, step_length = JOSEPHUS_NUM+1;
	//int begin_index = 2, step_length = 1;
	int begin_index = 2, step_length = 2;
	//int begin_index = 2, step_length = JOSEPHUS_NUM-1;
	//int begin_index = 2, step_length = JOSEPHUS_NUM;
	//int begin_index = 2, step_length = JOSEPHUS_NUM+1;

	L = josephus_create(JOSEPHUS_NUM);
	josephus_display(L);
	josephus_run(&L, begin_index, step_length);
	printf("At last:");
	josephus_display(L);
}

/*
 *@brief without head_node
 */
josephus_list* josephus_create(int num)
{
	josephus_list *p = NULL, *q=NULL, *L=NULL;
	int i = 1;

	L = (josephus_list*)malloc(sizeof(josephus_list));
	L->data = 1;
	L->next = L;

	p = L;
	for( i=2; i<=num; i++)
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
	if( L==NULL )
	{
		printf("empty list\n");
		return;
	}
	josephus_list *p = L;
	do
	{
		printf("%d ", p->data);
		p = p->next;
	}while( p != L);
	printf("\n");
}

/*
 * @param begin_index, count from this,[1-~)
 * @param step_length, step these time everytime,[1-~)
 */
void josephus_run(josephus_list** L, int begin_index, int step_length)
{

	if( L==NULL || *L==NULL )
	{
		printf("empty list\n");
		return;
	}
	int i = 1;
	josephus_list *p = *L, *q = NULL;
	while(i<begin_index)
	{
		p = p->next;
		++i;
	}

	printf("The point before begin_index:%d \n", p->data);

	while( !(p->next==p) )
	{
		i = 1;
		while( i<step_length )
		{
			p = p->next;
			++i;
		}

		q = p->next;
		p->next = q->next;
		printf("free %d\n", q->data);
		free(q);
	}

	*L = p;//The head L pointed maybe deleted already.
}

