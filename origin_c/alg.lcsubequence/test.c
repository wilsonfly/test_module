#include <stdio.h>
#include <string.h>

/*
最长公共子序列
输入："1A2C3D4B56","B1D23CA45B6A"

输出：6 （因为最大子序列是“123456”）

状态转移方程：

if(DP[i][j] == DP[i-1][j-1]):   DP[i][j] = DP[i-1][j-1]+1

else:   DP[i][j] = Max(DP[i-1][j], DP[i-1][j])
 */
/*
分析过程：

要求给定两个字符串，求它们的最长公共子序列的长度。这是一个经典的动态规划问题，可以使用动态规划来解决。

具体步骤如下：

1.定义一个二维数组dp，dp[i][j]表示text1的前i个字符和text2的前j个字符的最长公共子序列的长度。
2.初始化第一行和第一列，即dp[0][j]和dp[i][0]都为0，表示一个字符串为空时的最长公共子序列长度为0。
3.对于字符串text1的每个字符text1[i]，以及字符串text2的每个字符text2[j]：
 3.1如果text1[i]等于text2[j]，则dp[i][j] = dp[i-1][j-1] + 1，表示当前字符可以添加到最长公共子序列中。
 3.2否则，取text1前i个字符和text2前j-1个字符的最长公共子序列长度dp[i][j-1]，以及text1前i-1个字符和text2前j个字符的最长公共子序列长度dp[i-1][j]，的较大值作为dp[i][j]的值。
4.最终返回dp[m][n]，其中m是text1的长度，n是text2的长度，即两个字符串的最长公共子序列的长度。
 */
int longestCommonSubsequence(char* text1, char* text2) {
    int m = strlen(text1);
    int n = strlen(text2);

    int dp[m+1][n+1];
    int i,j;

    for (i = 0; i <= m; i++) {
        dp[i][0] = 0;
    }
    for (j = 0; j <= n; j++) {
        dp[0][j] = 0;
    }

    for (i = 1; i <= m; i++) {
        for (j = 1; j <= n; j++) {
            if (text1[i-1] == text2[j-1]) {
                dp[i][j] = dp[i-1][j-1] + 1;
            } else {
                dp[i][j] = (dp[i-1][j] > dp[i][j-1]) ? dp[i-1][j] : dp[i][j-1];
            }
        }
    }

#if 1
    //int i,j;
    for(i=0;i<m+1;i++)
    {
        for(j=0; j<n+1; j++)
        {
            printf("%d,",dp[i][j]);
        }
        printf("\n");
    }
#endif
    return dp[m][n];
}

//202307
#define MAX(x,y) (x)>(y)?(x):(y)
int longestCommonSubsequence2(char* text1, char* text2)
{
    int m = strlen(text1);
    int n = strlen(text2);
    int i,j;
    int dp[m+1][n+1];

    memset(dp,0,sizeof(dp));

    for(i=1; i<=m; i++)
    {
        for(j=1; j<=n; j++)
        {
            if(text1[i-1] == text2[j-1])
            {
                dp[i][j] = dp[i-1][j-1] + 1;
            }
            else
            {
                dp[i][j] = MAX(dp[i-1][j], dp[i][j-1]);
            }
        }
    }

    for(i=0; i<=m; i++)
    {
        for(j=0; j<=n; j++)
        {
            printf("%d,",dp[i][j]);
        }
        printf("\n");
    }
    return dp[m][n];

}

int main() {
    char text1[] = "1A2C3D4B56";
    char text2[] = "B1D23CA45B6A";

    //char text1[] = "abcdefgh";
    //char text2[] = "bdef";
    
    //char text1[] = "bdf";
    //char text2[] = "abcde";
    
    //char text1[] = "bd";
    //char text2[] = "d";
    
    //char text1[] = "d";
    //char text2[] = "d";
    
    //char text1[] = "d";
    //char text2[] = "";
    
    int length = longestCommonSubsequence(text1, text2);
    printf("The length of the longest common subsequence is: %d\n", length);

    int length2 = longestCommonSubsequence2(text1, text2);
    printf("The length of the longest common subsequence is: %d\n", length2);
    return 0;
}
