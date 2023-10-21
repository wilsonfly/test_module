#include <stdio.h>
#include <string.h>

/*
最长公共子串问题：
分析过程：
最长公共子串问题是指在两个字符串中寻找出现在相同位置的最长连续子串。可以使用动态规划的方法来解决该问题。

首先，创建一个二维数组dp来记录公共子串的长度。数组dp[i][j]表示以第一个字符串的第i个字符和第二个字符串的第j个字符结尾的公共子串的长度。

然后，遍历两个字符串的每个字符，当第一个字符串的第i个字符与第二个字符串的第j个字符相等时，将dp[i][j]设置为dp[i-1][j-1] + 1；否则将其设置为0。

在遍历的过程中，记录最大的子串长度以及对应的子串的结束位置，在遍历完成后即可得到最长公共子串的长度和起始位置。

最后，根据最长子串的长度和起始位置来提取最长公共子串。
 */
void longestCommonSubstring(char* str1, char* str2) {
    int len1 = strlen(str1);
    int len2 = strlen(str2);
    int dp[len1 + 1][len2 + 1];
    int maxLength = 0;
    int endPos = 0;
    int i,j;

    memset(dp, 0, sizeof(dp));

    for (i = 1; i <= len1; ++i) {
        for (j = 1; j <= len2; ++j) {
            if (str1[i - 1] == str2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
                if (dp[i][j] > maxLength) {
                    maxLength = dp[i][j];
                    endPos = i; // 记录最长子串的结束位置
                }
            } else {
                dp[i][j] = 0;
            }
        }
    }

    if (maxLength == 0) {
        printf("No common substring found.\n");
    } else {
        printf("Longest common substring: ");
        for (i = endPos - maxLength; i < endPos; ++i) {
            printf("%c", str1[i]);
        }
        printf("\n");
    }
}

//202307
void longestCommonSubstring2(char* str1, char* str2)
{
    int m = strlen(str1);
    int n = strlen(str2);
    int dp[m+1][n+1];//多准备全0列全0行
    int i,j;
    int maxLen=0,lastPos;
    int ret = -1;

    memset(dp, 0, sizeof(dp));

    for(i=1; i<=m; i++)
    {
        for(j=1; j<=n; j++)
        {
            if(str1[i-1] == str2[j-1])
            {
                dp[i][j] = dp[i-1][j-1] + 1;
                if(dp[i][j] > maxLen)
                {
                    maxLen = dp[i][j];
                    lastPos = i;
                }
            }
        }
    }

#if 1
    for(i=0; i<=m; i++)
    {
        for(j=0; j<=n; j++)
        {
            printf("%d,",dp[i][j]);
        }
        printf("\n");
    }
#endif
    if(maxLen ==0)
    {
        printf("not found\n");
    }
    else
    {
        ret = lastPos - maxLen;
        //for(i=lastPos-maxLen; i<=lastPos; i++)
        for(i=lastPos-maxLen; i<lastPos; i++)//dp数组多了全0列全0行,lastPos-1使用
        {
            printf("%c",str1[i]);
        }
        printf("\n");
    }
    return;
}

int main() {
    char str1[] = "1AB2345CD";
    char str2[] = "12345EF";
    
    //char str1[] = "1AB2345CD";
    //char str2[] = "1A";

    //char str1[] = "1AB2345CD";
    //char str2[] = "B2X";

    //char str1[] = "1AB2345CD";
    //char str2[] = "999";

    //char str1[] = "1AB2345CD";
    //char str2[] = "";


    longestCommonSubstring(str1, str2);

    longestCommonSubstring2(str1, str2);

    return 0;
}
