#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main ()
{
    int i;
    char str[] = "129t0h";
    char accept[] = "1234567890";
    i = strspn(str, accept);
    printf("str 前 %d 个字符都属于 accept\n",i);


    char* s1 = "http://c.biancheng.net/cpp/u/biaozhunku/";
    char* s2 = "c is good";
    int n = strcspn(s1,s2);
    printf("s1 前 %d 个字符都属于 s2\n",n);
    printf("The first char both in s1 and s2 is:%c\n",s1[n]);
    printf("The position in s1 is: %d\n",n);

    char* s3 = "http://c.biancheng.net/cpp/xitong/";
    char* s4 = "z -+*";
    if(strlen(s3) == strcspn(s3,s4)){
        printf("s3 is diffrent from s4!\n");
    }else{
        printf("There is at least one same character in s3 and s4!\n");
    }
    return 0;
}

