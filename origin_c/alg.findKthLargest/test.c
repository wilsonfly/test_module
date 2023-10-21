#include <stdio.h>
#include "llog_priv.h"


/*
题目描述
在一个未排序的数组中，找到第k 大的数字。
输入输出样例
输入一个数组和一个目标值k，输出第k 大的数字。题目默认一定有解。
Input: [3,2,1,5,6,4] and k = 2
Output: 5
题解
快速选择一般用于求解k-th Element 问题，可以在O¹nº 时间复杂度，O¹1º 空间复杂度完成求
解工作。快速选择的实现和快速排序相似，不过只需要找到第k 大的枢（pivot）即可，不需要对
其左右再进行排序。与快速排序一样，快速选择一般需要先打乱数组，否则最坏情况下时间复杂
度为O¹n2º，我们这里为了方便省略掉了打乱的步骤。
 */

int findKthLargetst(int data[], int size, int k)
{
    int mid = 0;
    int left = 0;
    int right = size-1;
    int targetIndex = size - k;//Kth Largest数据的index，比如1th:size-1, 2nd:size-2

    while(left < right)
    {
        mid = quickSelect(data, size, left, right);
        printf("left:%d,right:%d,mid:%d,targetIndex:%d\n",left,right,mid,targetIndex);
        if(mid == targetIndex)
        {
            return data[mid];
        }
        else if(mid < targetIndex)
            left = mid +1;
        else
            right = mid - 1;
    }
    return data[left];//left==right循环停掉,此时位置即可
}

//一轮快速排序，得到mid，即data[mid]在最终排序后的位置，即排序好一个数据
int quickSelect(int data[], int size, int startIndex, int endIndex)
{
    int left = startIndex;
    int right = endIndex;
    int pivot = data[startIndex];
    int tmp = 0;

    while(left != right)
    {
        while(left<right && pivot<=data[right])
            right--;
        while(left<right && pivot>=data[left])
            left++;
        if(left<right)
        {
            tmp = data[left];
            data[left] = data[right];
            data[right]= tmp;
        }
    }
    data[startIndex] = data[left];
    data[left] = pivot;

    show(data,size);
    return left;
}

void show(int array[], int size)
{
    int i;
    for(i=0;i<size;i++)
    {
        printf("[%d]%d,",i,array[i]);
    }
    printf("\n");

}

int main()
{
    int data[] = {3,2,1,5,6,4};
    int size = sizeof(data)/sizeof(int);

    show(data,size);
    printf("k:%d,ret:%d\n",1,findKthLargetst(data,size,1));
    printf("k:%d,ret:%d\n",2,findKthLargetst(data,size,2));
    printf("k:%d,ret:%d\n",3,findKthLargetst(data,size,3));

}
