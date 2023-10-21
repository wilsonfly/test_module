#include <stdio.h>

/*
题目描述
一个原本增序的数组被首尾相连后按某个位置断开（如[1,2,2,3,4,5] ! [2,3,4,5,1,2]，在第一
位和第二位断开），我们称其为旋转数组。给定一个值，判断这个值是否存在于这个为旋转数组
中。
输入输出样例
输入是一个数组和一个值，输出是一个布尔值，表示数组中是否存在该值。
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
 */
int searchRoundArray(int data[], int size, int target)
{
    int mid,left,right;

    left = 0;
    right = size-1;
    while(left <= right)
    {
        mid = (left+right)/2;
        //printf("left:%d/%d,mid:%d/%d,right:%d/%d\n",left,data[left],mid,data[mid],right,data[right]);
        if(data[mid] == target)
            return mid;

        if(data[mid] == data[right])//只能是mid~right的都相等
        {
            break;//上面已经判断target与mid位置数据，到此说明没有找到
        }
        else if (data[mid] > data[right])//左侧升序
        {
            if(target>=data[left] && target<data[mid])//target在此范围内
            {
                right = mid -1;//缩小一半范围
            }
            else
            {
                left = mid +1;//target不在此范围内，增加left使得下次去右侧查找
            }
        }
        else if(data[mid] < data[right])//右侧升序
        {
            if(target>data[mid] && target<=data[right])
            {
                left = mid + 1;//缩小一半范围
            }
            else
            {
                right = mid - 1;//target不在此范围内,减少right使得下次去左侧查找
            }
        }
    }
    return -1;
}

void show(int array[], int size)
{
    int i;
    for(i=0;i<size;i++)
    {
        printf("[%d]%d ",i,array[i]);
    }
    printf("\n");

}

/*
[0]3 [1]5 [2]6 [3]6 [4]8 [5]10 [6]13 [7]15 [8]0 [9]0 [10]1 [11]1 [12]2
search 13,ret:6
search 10,ret:5
search 15,ret:7
search 3,ret:0
search 2,ret:12
search 9,ret:-1
search 14,ret:-1
search 4,ret:-1
search 5,ret:1
search 6,ret:2
search 8,ret:4
search 1,ret:11

 */
int main()
{
    int data[] = { 3,5,6,6,8,10,13,15,0,0,1,1,2};
    int size = sizeof(data)/sizeof(int);

    show(data,size);
    printf("search 13,ret:%d\n",searchRoundArray(data,size,13));//中间位置
    printf("search 10,ret:%d\n",searchRoundArray(data,size,10));//靠近中间位置
    printf("search 15,ret:%d\n",searchRoundArray(data,size,15));//靠近中间位置
    printf("search 3,ret:%d\n",searchRoundArray(data,size,3));//左边界
    printf("search 2,ret:%d\n",searchRoundArray(data,size,2));//右边界
    printf("search 9,ret:%d\n",searchRoundArray(data,size,9));//不在数组内
    printf("search 14,ret:%d\n",searchRoundArray(data,size,14));//不在数组内
    printf("search 4,ret:%d\n",searchRoundArray(data,size,4));//不在数组内

    printf("search 5,ret:%d\n",searchRoundArray(data,size,5));
    printf("search 6,ret:%d\n",searchRoundArray(data,size,6));
    printf("search 8,ret:%d\n",searchRoundArray(data,size,8));
    printf("search 1,ret:%d\n",searchRoundArray(data,size,1));
#if 0
#endif
}
