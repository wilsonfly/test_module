#include <stdio.h>
#include <stdlib.h>

int partition( int *data,  int low, int high )
{
	int t = 0;
	t = data[low];
	while( low < high )
	{
		while( low < high && data[high] >= t) 
		{
			high--;
		}
		data[low] = data[high];
		while( low < high && data[low] <= t)
		{
			low++;
		}
		data[high] = data[low];
	}
	data[low] = t;
	//yanzheng:
	printf("high:%d low:%d\n",high,low);
	printf("the role:\n");
	for(t=0;t<8;t++)
	printf("%d ",data[t]);
	printf("\n");
	//
	return low;
}

void sort( int *data, int low, int high )
{
	if( low >= high )
	{
		return;
	}
	int pivotloc = 0;
	pivotloc = partition( data, low, high );
	sort( data, low, pivotloc-1 );
	sort( data, pivotloc+1, high );
}

void quit_sort( int *data, int n )
{
	sort(data, 0, n-1 );
}

int main()
{
	int i = 0;
	int data[] = {49, 38, 65, 97, 76, 13, 27, 50};

	quit_sort(data, sizeof(data)/sizeof(int));
	for( i = 0; i < sizeof(data)/sizeof(int); i++)
	{
		printf( "%d\n", data[i] );
	}	

	return 0;
}
