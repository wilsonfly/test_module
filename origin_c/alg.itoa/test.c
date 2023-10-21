#include <stdio.h>
/*
 *实现itoa接口
 */

char* myitoa(int n,char* str,int radix)
{
    char digit[]="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char* p = str;
    char* h = str;
    int tmp;

    if(str == NULL)
    {
        return str;
    }
    if( radix<2 || radix>36)
    {
        printf("only support radix:2-36i\n");
        return str;
    }

    //仅处理十进制的负数
    if(n < 0 && radix==10)
    {
        *p++='-';
        n=-n;
    }

    //用do-while方式可以支持n=0的情况
    do
    {
        *p++ = digit[n%radix];
        n /= radix;
    }while(n>0);
    *p = '\0';

    if(h[0] == '-')
        h++;

    //首尾交换
    for(p--;h<p;h++,p--)
    {
        tmp=*h;
        *h=*p;
        *p=tmp;
    }
    return str;
}

/*
myitoa,123,10:123
myitoa,123,16:7B
myitoa,123,8:173
myitoa,123,4:1323
myitoa,123,2:1111011
myitoa,-123,10:-123
myitoa,0,10:0

 */
int main()
{
    char buf[32];

    myitoa(123,buf,10);
    printf("myitoa,123,10:%s\n",buf);

    myitoa(123,buf,16);
    printf("myitoa,123,16:%s\n",buf);

    myitoa(123,buf,8);
    printf("myitoa,123,8:%s\n",buf);

    myitoa(123,buf,4);
    printf("myitoa,123,4:%s\n",buf);

    myitoa(123,buf,2);
    printf("myitoa,123,2:%s\n",buf);

    myitoa(-123,buf,10);
    printf("myitoa,-123,10:%s\n",buf);

    myitoa(0,buf,10);
    printf("myitoa,0,10:%s\n",buf);

}
