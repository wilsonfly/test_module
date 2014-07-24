#include <stdio.h>
#include <stdlib.h>

void bubble_sort1(int *data, int n)
{
	int i = 0;
	int j = 0;
	int t = 0;
	for( i = 0; i < n-1; i++ )
	{
		for( j = 0; j < n-1-i; j++ )
		{
			if( data[j] > data[j+1] )//j=n-2
			{
				t = data[j];
				data[j] = data[j+1];
				data[j+1] = t;
			}
		}
	}

}
void bubble_sort2(int *data, int n)
{
	int i = 0;
	int j = 0;
	int t = 0;
	int index = 0;
	for( i = 0; i < n-1; i++ )
	{
		index = 0;
		for( j = 0; j < n-1-i; j++ )
		{
			if( data[index] < data[j+1] )//j=n-2
			{
				index = j+1;
			}
		}
		//index n-1-i

		t = data[index];
		data[index] = data[n-1-i];
		data[n-1-i] = t;
	}

}
void bubble_sort3(int *data, int n)
{
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
			if( data[index] < data[j+1] )//j=n-2
			{
				index = j+1;
			}
			if(data[j] > data[j+1])
			{
				flag = 1;
			}
		}

		if(flag == 0)
			return;
		//index len-1-i

		t = data[index];
		data[index] = data[n-1-i];
		data[n-1-i] = t;
	}

}

int main()
{
	int i = 0, data[10];
	for ( i = 0; i < 10; i++ )
	{
		data[i] = 15-i;
	}
//	bubble_sort3(data, 10);
	bubble_sort2(data, 10);

	for( i = 0; i < 10; i++)
	{
		printf( "%d\n", data[i]);
	}	

	return 0;
}
