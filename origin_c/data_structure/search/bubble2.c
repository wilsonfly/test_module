#include <stdio.h>

//202307

//遍历一次找到最大值、最小值index，交换两次，但遍历次数为size/2
void bubble_sort3(int data[], int size)
{
    int i,j,tmp,max_index,min_index;
    
    for(i=0; i<size/2; i++)
    {
        min_index = i;
        max_index = i;
        for(j=i; j<=size-1-i; j++)
        {
            if(data[min_index]>data[j])
                min_index = j;
            if(data[max_index]<data[j])
                max_index = j;
        }
        j--;

        tmp = data[j];
        data[j] = data[max_index];
        data[max_index] = tmp;

        tmp = data[i];
        data[i] = data[min_index];
        data[min_index] = tmp;
    }
}

//遍历一遍找到最大值index，遍历这一次中交换一次数据
void bubble_sort2(int data[], int size)
{
    int i,j,tmp,max_index;
    for(i=0; i<size-1; i++)
    {
        max_index = 0;
        for(j=0; j<=size-1-i; j++)
        {
            if(data[max_index] < data[j])
            {
                max_index = j;
            }
        }
        j--;
        tmp = data[j];
        data[j] = data[max_index];
        data[max_index] = tmp;
    }
}

//每一步都交换数据
void bubble_sort1(int data[], int size)
{
    int i,j,tmp;
    for(i=0; i<size-1; i++)
    {
        for(j=0; j+1<=size-1-i; j++)
        {
            if(data[j] > data[j+1])
            {
                tmp = data[j];
                data[j] = data[j+1];
                data[j+1] = tmp;
            }
        }
    }

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
18,2,3,5,4,7,8,13,3,6,10,2,11,13,18,5,13,0,14,1,
0,1,2,2,3,3,4,5,5,6,7,8,10,11,13,13,13,14,18,18,
 */
int main()
{
    int data[] = {18,2,3,5,4,7,8,13,3,6,10,2,11,13,18,5,13,0,14,1};
    int size = sizeof(data)/sizeof(int);

    show(data,size);
    //bubble_sort1(data,size);
    //bubble_sort2(data,size);
    bubble_sort3(data,size);
    show(data,size);
}
