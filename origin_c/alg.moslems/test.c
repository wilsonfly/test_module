#include <stdio.h>
#include <string.h>

/*
题目描述
给定一个字符，求其有多少个回文子字符串。回文的定义是左右对称。
输入输出样例
输入是一个字符串，输出一个整数，表示回文子字符串的数量。
Input: "aaa"
Output: 6
六个回文子字符串分别是["a","a","a","aa","aa","aaa"]。
题解
我们可以从字符串的每个位置开始，向左向右延长，判断存在多少以当前位置为中轴的回文
子字符串。
以x为中轴分别出发搜索奇数个，以x,x+1分别出发搜索偶数个
 */

int countSubstrings(char*s)
{
    int i;
    int count=0;
    for(i=0; i<strlen(s); i++)
    {
        count += extendSubstrings(s,i,i);  //搜索奇数个
        count += extendSubstrings(s,i,i+1);//搜索偶数个
    }
    return count;
}

int extendSubstrings(char* s, int l, int r)
{
    int count = 0;
    while(l>=0 && r<strlen(s) && s[l]==s[r])
    {
        --l;
        ++r;
        count++;
    }
    return count;
}


/*
huiwen:aaa, substring num:6
huiwen:aba, substring num:4
huiwen:aab, substring num:4
huiwen:aabb, substring num:4
huiwen:aabaa, substring num:9

 */
int main()
{
    printf("huiwen:%s, substring num:%d\n","aaa",countSubstrings("aaa"));
    printf("huiwen:%s, substring num:%d\n","aba",countSubstrings("aba"));
    printf("huiwen:%s, substring num:%d\n","aab",countSubstrings("aab"));
    printf("huiwen:%s, substring num:%d\n","aabb",countSubstrings("aab"));
    printf("huiwen:%s, substring num:%d\n","aabaa",countSubstrings("aabaa"));

    
}
