#include <stdio.h>
#include <stdlib.h>

#define DATA_NUM 20
static void print_data(int* data, int num);
static void bubble_sort0(int* data, int size);
static void bubble_sort1(int* data, int size);
static void bubble_sort(int* data, int size);

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

	//bubble_sort0(data,sizeof(data)/sizeof(int));
	//bubble_sort1(data,sizeof(data)/sizeof(int));
	bubble_sort(data,sizeof(data)/sizeof(int));

	print_data(data,sizeof(data)/sizeof(int));

	return 0;
}

static void bubble_sort(int* data, int size)
{
	if( data==NULL || size<=1 )
		return;

	int i=0, j=0;
	int tmp = 0;
	int max_index = 0;
	int sort_flag = 0;
	for( i=0; i<size-1; i++ )
	{
		max_index = 0;
		for( j=0; j<size-1-i; j++ )
		{
			if(data[j+1]>data[max_index])/*border problem*/
			{
				max_index = j+1;
				//sort_flag = 1;/*only meaned data[0] is the minumun in this round*/
			}
			if(data[j]<data[j+1])
			{
				sort_flag = 1;
			}
		}

		if( sort_flag == 0 )
			return;

		tmp = data[j];/*j==size-1-n, the last position*/
		data[j] = data[max_index];
		data[max_index] = tmp;
	}
	return;
}

static void bubble_sort1(int* data, int size)
{
	if( data==NULL || size<=1 )
		return;

	int i=0, j=0;
	int tmp = 0;
	int max_index = 0;
	for( i=0; i<size-1; i++ )
	{
		max_index = 0;
		for( j=0; j<size-1-i; j++ )
		{
			if(data[j+1]>data[max_index])/*border problem*/
			{
				max_index = j+1;
			}
		}
		tmp = data[j];/*j==size-1-n, the last position*/
		data[j] = data[max_index];
		data[max_index] = tmp;
	}
	return;
}

static void bubble_sort0(int* data, int size)
{
	if( data==NULL || size<=1 )
		return;

	int i=0, j=0;
	int tmp = 0;
	for( i=0; i<size-1; i++ )
	{
		for( j=0; j<size-i-1; j++ )
		{
			if(data[j]>data[j+1])
			{
				tmp = data[j];
				data[j] = data[j+1];
				data[j+1] = tmp;
			}
		}
	}
	return;
}
static void print_data(int* data, int num)
{
	if( data==NULL || num==0 )
		return;

	int i = 0;
	for( i=0; i < num; i++ )
	{
		printf( "%d ", data[i]);
	}
	printf("\n");
}
