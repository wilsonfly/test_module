#include <stdio.h>
#include "llog_priv.h"

/*
题目描述
给定一个无重复数字的整数数组，共N个数据，从中取M个求其所有的排列方式。
输入输出样例
输入是一个一维整数数组，输出是一个二维数组，表示输入数组的所有排列方式。
Input: [1,2,3,4]
Output: [[1,2], [1,3], [2,1,3], [2,3], [3,2], [3,1]]
可以以任意顺序输出，只要包含了所有排列方式即可。
 */

#define N 4
#define M 2
void showProcessing(int data[], int size)
{
    int i;
    printf("processing[");
    for(i=0;i<size;i++)
    {
        printf("%d ",data[i]);
    }
    printf("]processing\n");
}

void show(int data[], int size)
{
    int i;
    printf("[");
    //for(i=0;i<size;i++)
    for(i=0;i<M;i++)
    {
        printf("%d ",data[i]);
    }
    printf("]\n");

}

void swap(int data[],int i, int j)
{
    int tmp;
    tmp = data[i];
    data[i] = data[j];
    data[j] = tmp;
}

void permute(int data[], int size, int offset)
{
    int i,j;
    static int count = 0;

    //if(offset == size-1)
    if(offset == M)
    {
        show(data,size);
        return;
    }

    for(i=offset; i<size; i++)
    {
        swap(data,i,offset);
        //printf("i:%d,offset:%d\n",i,offset);
        //showProcessing(data,size);
        permute(data,size,offset+1);
        swap(data,i,offset);
    }
}

/*
[1 2 ]
[1 3 ]
[1 4 ]
[2 1 ]
[2 3 ]
[2 4 ]
[3 2 ]
[3 1 ]
[3 4 ]
[4 2 ]
[4 3 ]
[4 1 ]
 */
int main()
{
    int data[] = {1,2,3,4};
    int size = sizeof(data)/sizeof(int);
    int offset = 0;

    permute(data, size, offset);

}
