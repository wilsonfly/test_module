#include <stdio.h>
#include <stdlib.h>

#define DATA_NUM 10

static void print_data(int* data, int num);
static void quick_sort(int* data, int size);
static void sort(int* data, int front, int rear);
static int partition(int* data, int front, int rear);

int main()
{
	int i = 0;
	int data[DATA_NUM];

	srand(4);
	for ( i = 0; i < DATA_NUM; i++ )
	{
		data[i] = (float)rand()/RAND_MAX*20;
	}

	print_data(data,sizeof(data)/sizeof(int));

	quick_sort(data,sizeof(data)/sizeof(int));
	//sort(data, 0, sizeof(data)/sizeof(int)-1 );

	print_data(data,sizeof(data)/sizeof(int));
}

static int partition(int* data, int front, int rear)
{
	printf("begin,front:%d, rear:%d \n", front, rear);
	if( data==NULL || front<0 || rear<0 )
		return;
	if( front >= rear )
		return;

	int tmp = 0;
	tmp = data[front];
	while( front < rear )
	{
		while( front<rear && tmp<=data[rear] )
			rear--;

		data[front] = data[rear];

		while( front<rear && tmp>=data[front] )
			front++;

		data[rear] = data[front];
	}
	data[front] = tmp;

	printf("front:%d,rear:%d,tmp:%d\n", front, rear, tmp);
	printf("end:");
	print_data(data, DATA_NUM);
	return front;/*front==rear, the pivot*/
}

static void sort(int* data, int front, int rear)
{
	if( data==NULL || front<0 || rear<0 )
		return;
	if( front >= rear )
		return;

	int pivot=0;

	pivot = partition(data, front, rear);
	sort(data, front, pivot-1);
	sort(data, pivot+1, rear);
}

static void quick_sort(int* data, int size)
{
	if( data==NULL || size<=1 )
		return;
	sort(data, 0, size-1);
}

static void print_data(int* data, int num)
{
	if( data==NULL || num==0 )
		return;

	int i = 0;
	for( i=0; i < num; i++ )
	{
		printf( "[%d]%d ", i, data[i]);
	}
	printf("\n");
}
