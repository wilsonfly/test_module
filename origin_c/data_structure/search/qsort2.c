#include <stdio.h>
#include <stdlib.h>

#define DATA_NUM 10

//双边循环
static int partition(int* data, int startIndex, int endIndex)
{
    int tmp;
    int pivot = data[startIndex];
    int left = startIndex;
    int right = endIndex;

    while(left != right)
    {
        while(left<right && data[right]>=pivot)
            right--;
        while(left<right && data[left]<=pivot)
            left++;

        if(left<right)
        {
            //交换left和right 指针所指向的元素
            tmp = data[right];
            data[right] = data[left];
            data[left] = tmp;
        }
    }

    //left==right,交换之后左侧都比pivot小，右侧都比pivot大
    //pivot(即data[startIndex])和指针重合点交换
    data[startIndex] = data[left];
    data[left] = pivot;
    return left;
}

static void quick_sort(int* data, int startIndex, int endIndex)
{
	if( data==NULL || startIndex<0 || endIndex<0 )
		return;
	if( startIndex >= endIndex )
		return;

	int pivot=0;

	pivot = partition(data, startIndex, endIndex);
	quick_sort(data, startIndex, pivot-1);
	quick_sort(data, pivot+1, endIndex);
}

static void print_data(int* data, int num)
{
	if( data==NULL || num==0 )
		return;

	int i = 0;
	for( i=0; i < num; i++ )
	{
		//printf( "[%d]%d ", i, data[i]);
		printf( "%d,", data[i]);
	}
	printf("\n");
}

/*
18,2,3,5,4,7,8,13,3,6,
2,3,3,4,5,6,7,8,13,18,
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

	print_data(data, DATA_NUM);

	quick_sort(data, 0, DATA_NUM-1);

	print_data(data, DATA_NUM);
}

