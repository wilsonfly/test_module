#include <stdio.h>
#include "llog_priv.h"

int main()
{
    printf("%d\n", sizeof("china\n"));//占据 6 个字节
    printf("%d\n", sizeof(""));//空串，只含有'\0'占据 1 个 BYTE
    printf("%d\n", sizeof("china\0usa\n"));//遇到第一个\0 截止因此不会输出 usa，但是 usa 还是占据内存且后面会隐含\0，占据 10byte
    printf("%d\n", sizeof("\\\"\377123\378"));//\377 占据 1BYTE \378 不能算一个，因为越界因此算 2BYTE，总共 9byte
    printf("%d\n", sizeof("\0xFF"));

    char str[5]="abcde";
    printf("%s",str);
    printf("====\n");

    char str2[3]="abcde";
    printf("%s",str2);
    printf("====\n");

    char a[100] = "china";
    a[1] = 'b';
    char* p = a;
    *p = 'm';
    puts(a);
    puts(p);
    printf("====\n");


    char buf[64];
    printf("键盘输入一些字符:\n");
    scanf("%s",buf);//只能接收空格之前的内容
    printf("%s",buf);
    printf("====\n");
    getchar();//接收键盘输入的回车键，如果后续还有接收键盘输入内容的话，需要把这个回车键吃掉

#if 1
    printf("键盘输入一些字符:\n");
    gets(buf);//unsafe
    puts(buf);
    printf("====\n");
#endif



    return 0;
}
