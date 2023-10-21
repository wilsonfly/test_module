#include <stdio.h>
#include <assert.h>

/*
 * 实现strcmp接口
 */

int mystrcmp(char* src, char* dest)
{
    int ret = 0;
#if 0
    if(src==NULL || dest==NULL)
    {
        printf("invalid param\n");
        return -1;//return 啥不合适，还是直接挂掉比较合适
    }
#endif
#if 0
    //问题点：实测如果传进来的是空字符串"",断言也会失败，Assertion `src==((void *)0)' failed.
    //这应该跟assert实现方式有关,取空字符串""中的内容'\0'进行的比较
    assert(src==NULL);
    assert(dest==NULL);
#endif

    do
    {
        ret = *src++ - *dest++;
    }while(ret==0 && (*src!='\0' && *dest!='\0'));
    
    if(*src=='\0' || *dest=='\0')
    {
        ret = *src - *dest;
        //printf("at lest one '0',[%c][%c],ret:%d\n",*src,*dest,ret);
    }
    return ret;
}

int mystrcmp2(char* src, char* dest)
{
#if 0
    int ret = 0;
    unsigned char* p1 = (unsigned char*)src;
    unsigned char* p2 = (unsigned char*)dest;
    while(!(ret=*p1 - *p2) && *p1 && *p2)
        ++p1,++p2;
    return ret;
#endif
    int ret = 0;
    while(!(ret=*src - *dest) && *src && *dest)
        ++src,++dest;
    return ret;
}

/*
[] cmp [123]:-49
[123] cmp []:49
[123] cmp [123]:0
[123] cmp [1234]:-52
[123] cmp [12]:51
[] cmp [123]:-49
[123] cmp []:49
[123] cmp [123]:0
[123] cmp [1234]:-52
[123] cmp [12]:51
]
 */
int main()
{

    char *buf0 = NULL;
    char buf1[] = "";
    char buf2[] = "123";
    char buf3[] = "123";
    char buf4[] = "1234";
    char buf5[] = "12";
    
    //printf("NULL cmp [%s]:%d\n",buf1,mystrcmp(buf0,buf1));//test param:NULL
    printf("[%s] cmp [%s]:%d\n",buf1,buf2,mystrcmp(buf1,buf2));
    printf("[%s] cmp [%s]:%d\n",buf2,buf1,mystrcmp(buf2,buf1));
    printf("[%s] cmp [%s]:%d\n",buf2,buf3,mystrcmp(buf2,buf3));
    printf("[%s] cmp [%s]:%d\n",buf2,buf4,mystrcmp(buf2,buf4));
    printf("[%s] cmp [%s]:%d\n",buf2,buf5,mystrcmp(buf2,buf5));

    //printf("NULL cmp [%s]:%d\n",buf1,mystrcmp2(buf0,buf1));//segmentation fault,没有空指针检查
    printf("[%s] cmp [%s]:%d\n",buf1,buf2,mystrcmp2(buf1,buf2));
    printf("[%s] cmp [%s]:%d\n",buf2,buf1,mystrcmp2(buf2,buf1));
    printf("[%s] cmp [%s]:%d\n",buf2,buf3,mystrcmp2(buf2,buf3));
    printf("[%s] cmp [%s]:%d\n",buf2,buf4,mystrcmp2(buf2,buf4));
    printf("[%s] cmp [%s]:%d\n",buf2,buf5,mystrcmp2(buf2,buf5));

}
