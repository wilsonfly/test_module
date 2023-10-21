#include <stdio.h>
#include "llog_priv.h"

/*
 * 问题描述：
 * 有一个数组a[n]，里边的数只有两种：-1和1。i，j是两个整数，假设0<=i<=j<=n-1，
 * 找出a[i]和a[j]中连续数之和最大的部分，如果最大部分存在相等的情况，则优先找出最短的。
 */

void findMaxSubarray(int arr[], int n)
{
    int maxSum=-n;
    int start,end;
    int sum,minLen;
    int i,j;

    for(i=0;i<n;i++)
    {
        sum=0;
        for(j=i;j<n;j++)
        {
            sum+=arr[j];
            if((sum>maxSum) ||(sum==maxSum && minLen>j-i+1))
            {
                maxSum=sum;
                minLen=j-i+1;
                start=i;
                end=j;
            }
        }
    }
    printf("start:%d,end:%d,maxSum:%d\n",start,end,maxSum);

}

int main()
{
    log();
    //int a[] = {-1, 1, 1, 1, -1, 1, -1, -1, 1, -1, 1};
    int a[] = {1, -1, -1, 1, 1, -1, 1, 1, 1, -1};
    int n = sizeof(a) / sizeof(a[0]);

    findMaxSubarray(a, n);

}
