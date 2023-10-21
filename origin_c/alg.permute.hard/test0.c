#include <stdio.h>
#include "llog_priv.h"

/*
题目描述
给定一个无重复数字的整数数组，求其所有的排列方式。
输入输出样例
输入是一个一维整数数组，输出是一个二维数组，表示输入数组的所有排列方式。
Input: [1,2,3]
Output: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,2,1], [3,1,2]]
可以以任意顺序输出，只要包含了所有排列方式即可。
 */

void show(int data[], int size)
{
    int i;
    printf("[");
    for(i=0;i<size;i++)
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

//有问题
void permute(int data[], int size, int offset)
{
    int i,j;
    static int count = 0;

    if(offset >= size-1)
        return;

    for(i=offset; i+1<size; i++)
    {
        for(j=i+1; j<size; j++)
        {
            //交换前作为一种组合
            show(data,size);
            count++;
            printf("count:%d\n",count);

            //交换后作为一种组合
            swap(data,i,j);

            show(data,size);
            count++;
            printf("count:%d\n",count);
            
            //交换后,剩余部分的组合
            permute(data,size,j);
        }
    }
}

int main()
{
    int data[] = {1,2,3};
    int size = sizeof(data)/sizeof(int);
    int offset = 0;

    permute(data, size, offset);

}
