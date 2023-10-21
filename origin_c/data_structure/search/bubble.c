#include <stdio.h>
#include <stdlib.h>

#define DATA_NUM 20
static void print_data(int* data, int num);
static void bubble_sort0(int* data, int size);
static void bubble_sort1(int* data, int size);
static void bubble_sort2(int* data, int size);
static void bubble_sort3(int* data, int size);
static void bubble_sort4(int* data, int size);


/*
18,2,3,5,4,7,8,13,3,6,10,2,11,13,18,5,13,0,14,1,
0,1,2,2,3,3,4,5,5,6,7,8,10,11,13,13,13,14,18,18,
 */
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
	//bubble_sort2(data,sizeof(data)/sizeof(int));
	//bubble_sort3(data,sizeof(data)/sizeof(int));
	bubble_sort4(data,sizeof(data)/sizeof(int));

	print_data(data,sizeof(data)/sizeof(int));

	return 0;
}

//202307
//遍历一轮，找到最大值、最小值的index，一轮之后分别放到最后、最前位置
static void bubble_sort4(int* data, int size)
{
    int j,k,tmp;
    int min_index,max_index;

    for(k=0; k<size/2; k++)// size/2轮即可
    {
        min_index = k;
        max_index = k;
        for(j=k; j<=size-1-k; j++)
        {
            if(data[min_index] > data[j])
                min_index = j;
            if(data[max_index] < data[j])
                max_index = j;
        }
        //k,size-1-k
        //if size==10, 0,9 1,8 2,7 3,6 4,5
        tmp = data[size-1-k];
        data[size-1-k] = data[max_index];
        data[max_index] = tmp;

        tmp = data[k];
        data[k] = data[min_index];
        data[min_index] = tmp;
    }
}

//202307
//遍历一轮找到最大值的index，一轮之后与最后一位数进行交换
static void bubble_sort3(int* data, int size)
{
    int i,j,max_index,tmp;

    //for(i=0; i<size; i++)//也能work
    for(i=0; i<size-1; i++)//找size-1次最大值即可
    {
        max_index = 0;
        for(j=0; j<=size-1-i; j++)
        {
            if(data[j] > data[max_index])
                max_index = j;
        }
        j--;//handle j overflow
        tmp = data[max_index];
        data[max_index] = data[j];
        data[j] = tmp;
    }
}

static void bubble_sort2(int* data, int size)
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
static void print_data(int* data, int size)
{
	if( data==NULL || size==0 )
		return;

	int i = 0;
    
    //printf("data size:%d\n",size);
	for( i=0; i < size; i++ )
	{
		printf( "%d,", data[i]);
	}
	printf("\n");
}
