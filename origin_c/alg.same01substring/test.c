#include <stdio.h>

/*
题目描述
给定一个0-1 字符串，求有多少非空子字符串的0 和1 数量相同。
输入输出样例
输入是一个字符串，输出一个整数，表示满足条件的子字符串的数量。
Input: "00110011"
Output: 6
在这个样例中，六个0 和1 数量相同的子字符串是["0011","01","1100","10","0011","01"]。
 */
/*
 *huasheng:举例不对
[00],substring of 0nums==1nums:0
[001],substring of 0nums==1nums:1
[0011],substring of 0nums==1nums:2
[00110],substring of 0nums==1nums:4
[001100],substring of 0nums==1nums:5
[00110011],substring of 0nums==1nums:10
[0011001],substring of 0nums==1nums:8
[001100111],substring of 0nums==1nums:10
[00110010],substring of 0nums==1nums:10
 */

int countSubstring(char* s)
{
    int i,j;
    int count=0;

    for(i=0; i<strlen(s)-1; i++)
    {
        for(j=1; i+j<strlen(s); )
        {
            count += checkNumEqual(s+i,s+i+j);
            j+=2;
        }
    }
    return count;
}

int checkNumEqual(char*s, char* endIndex)
{
    int zeroNum = 0;
    int oneNum = 0;
    char* p=s;
    for(;p<=endIndex; p++)
    {
        if(*p == '0')
            zeroNum++;
        if(*p == '1')
            oneNum++;
    }
#if 0
    for(p=s; p<=endIndex; p++)
    {
        printf("%c,",*p);
    }
    printf("\nzeornum:%d,oneNum:%d\n",zeroNum,oneNum);
#endif
    return zeroNum==oneNum?1:0; 
}

int main()
{
    char* s7 = "00";
    printf("[%s],substring of 0nums==1nums:%d\n",s7,countSubstring(s7));

    char* s6 = "001";
    printf("[%s],substring of 0nums==1nums:%d\n",s6,countSubstring(s6));

    char* s = "0011";
    printf("[%s],substring of 0nums==1nums:%d\n",s,countSubstring(s));

    char* s4 = "00110";
    printf("[%s],substring of 0nums==1nums:%d\n",s4,countSubstring(s4));

    char* s5 = "001100";
    printf("[%s],substring of 0nums==1nums:%d\n",s5,countSubstring(s5));

    char* s0 = "00110011";
    printf("[%s],substring of 0nums==1nums:%d\n",s0,countSubstring(s0));

    char* s1 = "0011001";
    printf("[%s],substring of 0nums==1nums:%d\n",s1,countSubstring(s1));
    
    char* s2 = "001100111";
    printf("[%s],substring of 0nums==1nums:%d\n",s2,countSubstring(s2));
    
    char* s3 = "00110010";
    printf("[%s],substring of 0nums==1nums:%d\n",s3,countSubstring(s3));
    
}
