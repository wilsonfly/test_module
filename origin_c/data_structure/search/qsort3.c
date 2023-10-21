#include <stdio.h>

//202307
int partition(int data[], int startIndex, int endIndex)
{
    int left = startIndex;
    int right = endIndex;
    int pivot = data[startIndex];
    int tmp;

    while(left != right)
    {
        while(left<right && pivot <= data[right])//停止条件：要么找到比pivot小的值，要么left==right
            right--;
        while(left<right && pivot >= data[left])//停止条件：要么找到比pivot大的值，要么left==right
            left++;

        //if(data[left]>data[right])//大概率成立除非相等也就是left==right,左侧找到的是首个比pivot大的,右侧找到的是首个比pivot小的
        if(left < right)//这里其实是在left==right的时候不用交换了
        {
            tmp = data[left];
            data[left] = data[right];
            data[right] = tmp;
        }
    }

    data[startIndex] = data[left];
    data[left] = pivot;
    
    return left;
}

int quick_sort(int data[], int startIndex, int endIndex)
{
    //递归调用，结束条件
    if(startIndex >= endIndex)
        return;

    int pivotIndex = partition(data,startIndex,endIndex);
    quick_sort(data,startIndex,pivotIndex-1);
    quick_sort(data,pivotIndex+1,endIndex);
}

void show(int* data, int size)
{
	if( data==NULL || size==0 )
		return;

	int i = 0;
	for( i=0; i < size; i++ )
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
    int data[] = {18,2,3,5,4,7,8,13,3,6};
    int size = sizeof(data)/sizeof(int);

    show(data,size);
    quick_sort(data, 0, size-1);
    show(data,size);

}
