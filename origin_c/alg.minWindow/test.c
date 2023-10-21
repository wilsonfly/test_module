#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
题目描述
给定两个字符串S 和T，求S 中包含T 所有字符的最短连续子字符串的长度，同时要求时间
复杂度不得超过O¹nº。
输入输出样例
输入是两个字符串S 和T，输出是一个S 字符串的子串。
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
在这个样例中，S 中同时包含一个A、一个B、一个C 的最短子字符串是“BANC”。
 */

/*
我们可以使用滑动窗口的方法来解决这个问题。首先，我们需要定义一个窗口，该窗口包含了t中的所有字符。
然后，我们可以通过移动窗口的左边界和右边界来寻找最短的子字符串。

具体步骤如下：

1.首先，我们需要统计字符串t中每个字符的出现次数，保存在一个哈希表中。
2.初始化左指针left和右指针right，分别指向s的起始位置。
3.初始化一个计数器count，用于记录窗口中已经包含了t中的字符的数量。
4.初始化最短子字符串的起始位置start和长度min_len，初始值为0和一个较大的数。
5.开始滑动窗口，通过移动右指针right来扩展窗口。每次移动时，更新窗口中字符的出现次数和计数器count。
6.当窗口中包含了t中的所有字符时，尝试移动左指针left来缩小窗口，同时更新最短子字符串的起始位置和长度。
7.重复步骤5和步骤6，直到右指针right到达字符串s的末尾。
8.返回最短子字符串。
 */
//202307
char* minWindow(char* s, char* t)
{
    if(s==NULL || t==NULL)
        return "";
    
    int sLen = strlen(s);
    int tLen = strlen(t);
    if(sLen<tLen || tLen==0)
        return "";

    int sHash[128] = {0};
    int tHash[128] = {0};
    int i = 0;
    for(i=0; i<tLen; i++)
    {
        tHash[t[i]]++;//统计t中每个字符个数
    }

    int left = 0;
    int right = 0;
    int minWindow = sLen;//sLen + 1;
    int windowLen = 0;
    int startIndex = 0;
    int count = 0;

    while(right < sLen)
    {
        sHash[s[right]]++;//统计s中每个字符个数

        if(sHash[s[right]] <= tHash[s[right]])//s中首次出现t中字符进行统计
            count++;

        //找到一组t中所有字符
        if(count == tLen)
        {
            //在已经找到一组t后，再次出现t的字符，触发while，左移left
            while(sHash[s[left]] > tHash[s[left]])
            {
                sHash[s[left]]--;
                left++;
            }

            //更新窗口长度和起始位置
            windowLen = right - left + 1;
            if(windowLen < minWindow)
            {
                minWindow = windowLen;
                startIndex = left;
            }
        }
        right++;
    }
    
    if(minWindow == sLen)//s中没有找到t
        return "";
    
    char* ret = (char*)malloc(minWindow+1);
    strncpy(ret, s+startIndex, minWindow);
    return ret;
}

int main() {
    //char s[] = "ADOBECODEBANC";
    //char s[] = "AADOBEFCODEBANC";
    char s[] = "ADOBEFCODEBANC";
    char t[] = "ABC";
    
    char* result = minWindow(s, t);
    printf("Output: %s\n", result);
    
    return 0;
}
