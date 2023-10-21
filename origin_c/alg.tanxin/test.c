#include <stdio.h>
#include "llog_priv.h"

/*
题目描述
有一群孩子和一堆饼干，每个孩子有一个饥饿度，每个饼干都有一个大小。每个孩子只能吃
一个饼干，且只有饼干的大小不小于孩子的饥饿度时，这个孩子才能吃饱。求解最多有多少孩子
可以吃饱。
输入输出样例
输入两个数组，分别代表孩子的饥饿度和饼干的大小。输出最多有多少孩子可以吃饱的数
量。
Input: [1,2], [1,2,3]
Output: 2
在这个样例中，我们可以给两个孩子喂[1,2]、[1,3]、[2,3] 这三种组合的任意一种。
题解
因为饥饿度最小的孩子最容易吃饱，所以我们先考虑这个孩子。为了尽量使得剩下的饼干可
以满足饥饿度更大的孩子，所以我们应该把大于等于这个孩子饥饿度的、且大小最小的饼干给这
个孩子。满足了这个孩子之后，我们采取同样的策略，考虑剩下孩子里饥饿度最小的孩子，直到
没有满足条件的饼干存在。
 */
int findContentChildren(int child[], int size1, int cookies[], int size2)
{
    int i,j;
    for(i=0,j=0; i<size1&&j<size2;)
    {
        if(child[i] <= cookies[j])
            i++;
        j++;
    }
    return i;

}

void show(int array[], int size)
{
    int i;
    for(i=0;i<size;i++)
    {
        printf("%d,",array[i]);
    }
    printf("\n");
}

int main()
{
    int child[] = {1,3,4,6};
    int cookies[] = {1,2,4,5};
    int ret = 0;
    int size1 = sizeof(child)/sizeof(int);
    int size2 = sizeof(cookies)/sizeof(int);

    ret = findContentChildren(child,size1,cookies,size2);
    printf("%d\n",ret);
    show(child,size1);
    show(cookies,size2);

}
