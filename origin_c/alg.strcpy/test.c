#include <stdio.h>
#include <string.h>//打印语句中strlen需要
/*
 *实现strcpy接口
 */

char* mystrcpy(char* dest, char* src)
{
    if(src==NULL || dest==NULL)
    {
        printf("param NULL\n");
        return NULL;
    }

    do
    {
        *dest++ = *src;
    }
    while( *src++ != '\0');
    //能处理空字符串"",也能拷贝字符串结束符'\0'

    return dest;
}

/*
param NULL
param NULL
dest1,strlen:0,[]
dest1,strlen:10,[1234567890]
dest2,strlen:10,[1234567890]
 */
int main()
{

    char *src0 = NULL;
    char *src1 = "";
    char *src2 = "1234567890";

    char *dest0 = NULL;
    char dest1[8];//从实际结果来看，使用了分配空间之外的空间
    char dest2[16];
    
    mystrcpy(dest0,src0);
    mystrcpy(dest1,src0);
    mystrcpy(dest1,src1);
    printf("dest1,strlen:%d,[%s]\n",strlen(dest1),dest1);
    mystrcpy(dest1,src2);
    printf("dest1,strlen:%d,[%s]\n",strlen(dest1),dest1);
    mystrcpy(dest2,src2);
    printf("dest2,strlen:%d,[%s]\n",strlen(dest2),dest2);
}
