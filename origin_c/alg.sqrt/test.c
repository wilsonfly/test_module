#include <stdio.h>
#include "llog_priv.h"

/*
给定一个非负整数，求它的开方，向下取整。
 */
int mySqrt_biSearch(int x)
{
    int mid = 0;
    int left = 1;
    int right = x/2;
    int n = 0;

    while(left < right)
    {
        mid = (left + right)/2 + 1;
        n = mid*mid;
        printf("left:%d,right:%d,mid:%d\n",left, right, mid);

        if(n > x)
            right = mid-1;
        else
            left = mid;
    }
    return left;
}

//注意：返回mid部分情况下有问题！！！
int mySqrt_biSearch2(int x)
{
    int mid = 0;
    int left = 1;
    int right = x/2;
    int n = 0;

    if( x <= 3 )
        return 1;

    //1,2,3 -->right=0
    while(left < right)
    {
        //mid = (left + right)>>1 + 1;
        //mid = (left + right)/2;
        mid = (left + right)/2 + 1;//能使得right减少而退出循环
        n = mid*mid;
        printf("left:%d,right:%d,mid:%d\n",left, right, mid);

        if(n > x)
            right = mid-1;
        else if(n < x)
            left = mid;//mid+1;
        else if(n == x)
            break;
    }
    printf("will return,left:%d,right:%d,mid:%d\n",left, right, mid);
    return mid;//x=14/15,[left:right] [1,7]m=5, [1,4]m=3, [3,4]m=4 -->4错误, 18/19也不对
}


int main()
{
    int num;
    int ret;

    printf("input one num to search:\n");
    scanf("%d",&num);
    ret = mySqrt_biSearch(num);
    printf("ret:%d\n",ret);

    ret = mySqrt_biSearch2(num);
    printf("ret2:%d\n",ret);
}
