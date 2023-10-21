#include <stdio.h>


/*
辗转相除法， 又名欧几里得算法（Euclidean algorithm），该算法的目的
是求出两个正整数的最大公约数。它是已知最古老的算法， 其产生时间可追溯至公
元前300年前。
这条算法基于一个定理：两个正整数a和b（a>b），它们的最大公约数等于a除
以b的余数c和b之间的最大公约数。
例如10和25，25除以10商2余5，那么10和25的最大公约数，等同于10和5的最
大公约数。
 */
int getGreatestCommonDivisor(int a, int b)
{
    int big = a>b?a:b;
    int small = a>b?b:a;
    int val = big%small;
    if(val ==0)
        return small;
    return getGreatestCommonDivisor(val,small);
}


/*
更相减损术，出自中国古代的《九章算术》，也是一种求最大公约数的算法。
它的原理更加简单：两个正整数a和b（a>b），它们的最大公约数等于a-b的差
值c和较小数b的最大公约数。例如10和25，25减10的差是15，那么10和25的最大
公约数，等同于10和15的最大公约数。
 */
int getGreatestCommonDivisor2(int a, int b)
{
    if(a == b)
        return a;

    int big = a>b?a:b;
    int small = a>b?b:a;
    int val = big - small;

    return getGreatestCommonDivisor2(val,small);

}

/*
把辗转相除法和更相减损
术的优势结合起来，在更相减损术的基础上使用移位运算。
众所周知，移位运算的性能非常好。对于给出的正整数a和b，不难得到如下的
结论。
（从下文开始，获得最大公约数的方法getGreatestCommonDivisor被简写为
gcd。）
当a和b均为偶数时，gcd(a,b) = 2×gcd(a/2, b/2) = 2×gcd(a>>1,b>>1)。
当a为偶数，b为奇数时，gcd(a,b) = gcd(a/2,b) = gcd(a>>1,b)。
当a为奇数，b为偶数时，gcd(a,b) = gcd(a,b/2) = gcd(a,b>>1)。
当a和b均为奇数时，先利用更相减损术运算一次，gcd(a,b) = gcd(b,a-b)，此
时a-b必然是偶数，然后又可以继续进行移位运算。
 */
int getGreatestCommonDivisor3(int a, int b)
{
    if(a == b)
        return a;
    if( (a&1)==0 && (b&1)==0 )
    {
        return getGreatestCommonDivisor3(a>>1,b>>1)<<1;
    }
    else if( (a&1)==0 && (b&1)!=0)
    {
        return getGreatestCommonDivisor3(a>>1,b);
    }
    else if( (a&1)!=0 && (b&1)==0)
    {
        return getGreatestCommonDivisor3(a,b>>1);
    }
    else
    {
        int big = a>b?a:b;
        int small = a>b?b:a;
        int val = big - small;
        return getGreatestCommonDivisor2(val,small);
    }
}


int main()
{
    printf("10,5,val:%d\n",getGreatestCommonDivisor(10,5));
    printf("10,25,val:%d\n",getGreatestCommonDivisor(10,25));
    printf("36,24,val:%d\n",getGreatestCommonDivisor(36,24));
    printf("30,36,val:%d\n",getGreatestCommonDivisor(30,36));

    printf("10,25,val:%d\n",getGreatestCommonDivisor2(10,25));
    printf("36,24,val:%d\n",getGreatestCommonDivisor2(36,24));
    printf("30,36,val:%d\n",getGreatestCommonDivisor2(30,36));

    printf("10,25,val:%d\n",getGreatestCommonDivisor3(10,25));
    printf("36,24,val:%d\n",getGreatestCommonDivisor3(36,24));
    printf("30,36,val:%d\n",getGreatestCommonDivisor3(30,36));
}
