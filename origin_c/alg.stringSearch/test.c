#include <stdio.h>
#include <string.h>

//此外还有BM(Boyer Moore)算法(最坏O(n*m))最好(n/m)、KMP算法O(m+n)
//BM比KMP快3-5倍,实践中一般用BM算法

/*
判断一个字符串是不是另一个字符串的子字符串，并返回其位置。
Input: haystack = "hello", needle = "ll"
Output: 2
 */

//把startindex-endIndex直接字符简单相加模拟hash
int hash(char* str, int startIndex, int endIndex)
{
    int ret = 0;
    int i;

    for(i=startIndex; i<=endIndex; i++)
    {
        ret += str[i];
    }
    return ret;
}
//RK算法:引入hash，避免每轮比对tLen次
//时间复杂度O(n),但hash冲突极端情况下退化为BF
int stringSearchHash(char* s, char* t)
{
    int tHash,sHash;
    int sLen = strlen(s);
    int tLen = strlen(t);
    int i,j;

    sHash = hash(s,0,tLen-1);
    tHash = hash(t,0,tLen-1);

    for(i=0; i<=sLen-tLen; i++)
    {
        //printf("i:%d,shash:%d,thash:%d\n",i,sHash,tHash);
        if(sHash != tHash)
        {
            sHash = sHash-s[i]+s[i+tLen];
            //printf("hash not equal,i:%d,new shash:%d\n",i,sHash);
            continue;
        }
        for(j=0; j<tLen; j++)
        {
            if(s[i+j] != t[j])
            {
                sHash = sHash-s[i]+s[i+tLen];//这里记得更新hash,再进入下次循环
                break;
            }
        }
        if(j == tLen)
            return i;
    }
    return -1;
}

//BF算法：Brute-Force暴力算法
//时间复杂度为O(nm)
int stringSearchBF(char* s,char* t)
{
    int sLen = strlen(s);
    int tLen = strlen(t);
    int i,j;

    if(sLen < tLen)
        return -1;

    for(i=0; i<=sLen-tLen; i++)
    {
        for(j=0; j<tLen; j++)
        {
            if(s[i+j] != t[j])
            {
                break;
            }
        }
        if(j == tLen)
            return i;
    }
    return -1;
}

int main()
{
    //char* s = "ABCABCAABCABCD";
    char* s = "ABCABCAEFABCABCD";
    char* t = "ABCABCD";
    char* t2 = "ABCA";
    char* t3 = "ABCD";
    char* t4 = "ABCY";

    printf("%s search %s,ret:%d\n",s,t,stringSearchBF(s,t));
    printf("%s search %s,ret:%d\n",s,t2,stringSearchBF(s,t2));
    printf("%s search %s,ret:%d\n",s,t3,stringSearchBF(s,t3));
    printf("%s search %s,ret:%d\n",s,t4,stringSearchBF(s,t4));

    printf("==============\n");

    printf("%s search %s,ret:%d\n",s,t,stringSearchHash(s,t));
    printf("%s search %s,ret:%d\n",s,t2,stringSearchHash(s,t2));
    printf("%s search %s,ret:%d\n",s,t3,stringSearchHash(s,t3));
    printf("%s search %s,ret:%d\n",s,t4,stringSearchHash(s,t4));
}
