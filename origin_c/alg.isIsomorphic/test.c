#include <stdio.h>
#include <stdbool.h>
#include <string.h>

/*
题目描述
判断两个字符串是否同构。同构的定义是，可以通过把一个字符串的某些相同的字符转换成
另一些相同的字符，使得两个字符串相同，且两种不同的字符不能够被转换成同一种字符。
输入输出样例
输入两个字符串，输出一个布尔值，表示两个字符串是否满足条件。
Input: s = "paper", t = "title"
Output: true

题解
我们可以将问题转化一下：记录两个字符串每个位置的字符第一次出现的位置，如果两个字
符串中相同位置的字符与它们第一次出现的位置一样，那么这两个字符串同构。举例来说，对于
“paper”和“title”，假设我们现在遍历到第三个字符“p”和“t”，发现它们第一次出现的位置都
在第一个字符，则说明目前位置满足同构。
*/
bool isIsomorphic(char* src, char* dst)
{
    if(strlen(src) != strlen(dst))
        return false;

    int len = strlen(src);
    int hashLen = 128;
    int sHash[128],dHash[128];
    int i,j;

    for(i=0; i<hashLen; i++)
    {
        sHash[i] = -1;
        dHash[i] = -2;
    }
    for(i=0; i<len; i++)
    {
        sHash[src[i]] = sHash[src[i]] >= 0 ? sHash[src[i]] : i;
    }

    for(i=0; i<len; i++)
    {
        dHash[dst[i]] = dHash[dst[i]] >= 0? dHash[dst[i]] : i;
    }

    for(i=0; i<len; i++)
    {
        if(sHash[src[i]] != dHash[dst[i]])
            return false;
    }

    return true;
}

bool isIsomorphic2(char* s, char* t) {
    int i, n = strlen(s);
    int map1[256] = {0}, map2[256] = {0}; // 初始化两个哈希表
    
    for (i = 0; i < n; i++) {
        // 检查当前字符映射关系
        if (map1[s[i]] != map2[t[i]])
            return false;
        
        // 更新字符的映射关系
        map1[s[i]] = i + 1;
        map2[t[i]] = i + 1;
    }
    
    return true;
}

int main()
{
    //true
    char* src = "paper";
    char* dst = "title";

    //true
    char* src1 = "papex";
    char* dst1 = "title";

    //true
    char* src2 = "paper";
    char* dst2 = "titlr";

    //true
    char* src3 = "paper";
    char* dst3 = "titlr";

    //false
    char* src4 = "papea";
    char* dst4 = "titlr";

    //false
    char* src5 = "papea";
    char* dst5 = "titl";

    printf("src:%s,dst:%s,isomorphic:%d\n",src,dst,isIsomorphic(src,dst));
    printf("src:%s,dst:%s,isomorphic:%d\n",src1,dst1,isIsomorphic(src1,dst1));
    printf("src:%s,dst:%s,isomorphic:%d\n",src2,dst2,isIsomorphic(src2,dst2));
    printf("src:%s,dst:%s,isomorphic:%d\n",src3,dst3,isIsomorphic(src3,dst3));
    printf("src:%s,dst:%s,isomorphic:%d\n",src4,dst4,isIsomorphic(src4,dst4));
    printf("src:%s,dst:%s,isomorphic:%d\n",src5,dst5,isIsomorphic(src5,dst5));

    printf("src:%s,dst:%s,isomorphic:%d\n",src,dst,isIsomorphic2(src,dst));
    printf("src:%s,dst:%s,isomorphic:%d\n",src1,dst1,isIsomorphic2(src1,dst1));
    printf("src:%s,dst:%s,isomorphic:%d\n",src2,dst2,isIsomorphic2(src2,dst2));
    printf("src:%s,dst:%s,isomorphic:%d\n",src3,dst3,isIsomorphic2(src3,dst3));
    printf("src:%s,dst:%s,isomorphic:%d\n",src4,dst4,isIsomorphic2(src4,dst4));
    printf("src:%s,dst:%s,isomorphic:%d\n",src5,dst5,isIsomorphic2(src5,dst5));

}
