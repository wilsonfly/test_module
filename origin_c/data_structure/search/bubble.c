#include <stdio.h>
#include <stdlib.h>

static void print_data(int* data, int num);

static void bubble_sort1(int *data, int n)
{
	if( data==NULL || n<=1 )
		return;
	int i = 0;
	int j = 0;
	int t = 0;
	for( i = 0; i < n-1; i++ )
	{
		for( j = 0; j < n-1-i; j++ )
		{
			if( data[j] > data[j+1] )
			{
				t = data[j];
				data[j] = data[j+1];
				data[j+1] = t;
			}
		}
	}

}

static void bubble_sort2(int *data, int n)
{
	if( data==NULL || n<=1 )
		return;
	int i = 0;
	int j = 0;
	int t = 0;
	int index = 0;
	for( i = 0; i < n-1; i++ )
	{
		index = 0;
		for( j = 0; j < n-1-i; j++ )
		{
			if( data[index] < data[j+1] )
			{
				index = j+1;
			}
		}

		t = data[index];
		data[index] = data[n-1-i];
		data[n-1-i] = t;
	}

}

static void bubble_sort3(int *data, int n)
{
	if( data==NULL || n<=1 )
		return;
	int i = 0;
	int j = 0;
	int t = 0;
	int index = 0;
	int flag = 0;
	for( i = 0; i < n-1; i++ )
	{
		index = 0;
		for( j = 0; j < n-1-i; j++ )
		{
			if( data[index] < data[j+1] )
			{
				index = j+1;
			}
			if(data[j] > data[j+1])
			{
				flag = 1;
			}
		}

		//if already sorted,do nothing anymore
		if(flag == 0)
			return;

		t = data[index];
		data[index] = data[n-1-i];
		data[n-1-i] = t;
	}
}

int main()
{
	int i = 0;
	int data[10];

	srand(3);
	for ( i = 0; i < 10; i++ )
	{
		data[i] = (float)rand()/RAND_MAX*20;
	}

	print_data(data,sizeof(data)/sizeof(int));

	//	bubble_sort1(data,sizeof(data)/sizeof(int));
	//	bubble_sort2(data,sizeof(data)/sizeof(int));
	bubble_sort3(data,sizeof(data)/sizeof(int));

	print_data(data,sizeof(data)/sizeof(int));

	return 0;
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
