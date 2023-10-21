#include <stdio.h>
#include "llog_priv.h"


/*
最大上升子序列
输入：2 1 5 3 6 4 8 9 7
输出：5
状态转移方程：    if( Arr[i]>Arr[j] && dp[i]<dp[j]+1 )  dp[i] = dp[j]+1)
 */

int maxAscendingSubsequence(int data[], int size)
{
    int dp[size];
    int i,j,max;

    for(i=0; i<size; i++)
    {
       dp[i] = 1; 
    }

    for(i=1; i<size; i++)
    {
        for(j=0; j<i; j++)
        {
            if(data[i]>data[j] && dp[j]+1>dp[i])
            {
                dp[i] = dp[j] + 1;
            }
        }
    }

    max = dp[0];
    for(i=1; i<size; i++)
    {
        printf("%d,",dp[i]);
        if(max < dp[i])
            max = dp[i];
    }
    return max;
}

int main() {
    int data[] = {2, 1, 5, 3, 6, 4, 8, 9, 7};
    //int data[] = {2, 1, 5, 3, 6};
    int size = sizeof(data)/sizeof(int);

    int result = maxAscendingSubsequence(data, size);

    printf("\nMax ascending subsequence length: %d\n", result);

    return 0;
}
