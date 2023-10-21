#include <stdio.h>
/*
题目描述
给定两个有序数组，把两个数组合并为一个。
输入输出样例
输入是两个数组和它们分别的长度m 和n。其中第一个数组的长度被延长至m + n，多出的
n 位被0 填补。题目要求把第二个数组归并到第一个数组上，不需要开辟额外空间。
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: nums1 = [1,2,2,3,5,6]
 */

void show(int array[], int size)
{
    int i;
    for(i=0;i<size;i++)
    {
        printf("%d,",array[i]);
    }
    printf("\n");
}

/*
2,5,6,0,0,0,
1,2,3,
1,2,2,3,5,6,

1,2,3,0,0,0,
2,5,6,
1,2,2,3,5,6,

 */
int main()
{
#if 0
    int data0[] = {2,5,6};//原始数组
    int data1[] = {2,5,6,0,0,0};//扩充后数组
    int data2[] = {1,2,3};
#endif
    //int data0[] = {1,2,3};//原始数组
    int data0[] = {1,4,7};//原始数组
    int data1[] = {1,4,7,0,0,0};//扩充后数组
    int data2[] = {2,5,6};

    int size0 = sizeof(data0)/sizeof(int);
    int size1 = sizeof(data1)/sizeof(int);
    int size2 = sizeof(data2)/sizeof(int);

    int p1 = size0 - 1;
    int p2 = size2 - 1;
    int p  = size1 - 1;

    show(data1,size1);
    show(data2,size2);

    while(p1>=0 && p2>=0)
    {
        if(data1[p1] >= data2[p2])
            data1[p--] = data1[p1--];
        else
            data1[p--] = data2[p2--];
    }
    while(p2>=0)
    {
        data1[p--] = data2[p2--];
    }

    show(data1,size1);
}
