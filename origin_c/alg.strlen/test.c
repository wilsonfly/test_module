#include <stdio.h>
/*
 *实现strlen接口
 */

int mystrlen(char* src)
{
    int num = 0;
    if(!src)
        return 0;

    while(*src++ != '\0')
        num++;
    return num;
}

/*
src0,mystrlen:0
src1,mystrlen:0
src2,mystrlen:10

 */
int main()
{

    char *src0 = NULL;
    char *src1 = "";
    char *src2 = "1234567890";

    printf("src0,mystrlen:%d\n",mystrlen(src0));
    printf("src1,mystrlen:%d\n",mystrlen(src1));
    printf("src2,mystrlen:%d\n",mystrlen(src2));
}
