#include <stdio.h>
#include "llog_priv.h"

#include "stdio.h"
#include "stdarg.h"

void simple_va_fun(int start, ...);
int demo( char* msg, ... );
int demo_new( char* msg, int aNum, ... );
int vspf(char *fmt, ...);

#define bufsize 80
char buffer[bufsize];

int main(int argc, char* argv[])
{
    simple_va_fun(100,-1);
    simple_va_fun(100,200,-1);

    demo("DEMO", "This", "is", "a", "demo!", "");
    demo_new("DEMO", 99, "This", "is", "a", "demo!", "");

    int inumber = 30;
    float fnumber = 90.0;
    char string[4] = "abc";
    vspf("%d %f %s", inumber, fnumber, string);
    printf("%s\n", buffer);

    return 0;
}

void simple_va_fun(int start, ...)
{
    va_list ap;
    int value =start;
    int count=0;
    va_start(ap,start);
    do
    {
        ++count;
        printf("the %d th arg: %d\n",count,value);
        value = va_arg(ap,int);                      //get the value of next param
    } while(value != -1);               
    return;
}

int demo( char* msg, ... )
{
    va_list argp;
    int argno = 0;
    char* para;

    printf("The first one is: %s\n", msg);

    va_start( argp, msg );
    while(1)
    {
        para = va_arg( argp, char*);
        if (strcmp(para, "") == 0 )
            break;
        printf("Parameter #%d is: %s\n", argno, para);
        argno++;
    }
    va_end( argp );
    return 0;
}

int demo_new( char* msg, int aNum, ... )
{
    va_list argp;
    int argno = 0;
    char* para;

    printf("The first one is: %s\n", msg);
    printf("The second one is: %d\n", aNum);

    va_start( argp, aNum );//the one just before ...
    while(1)
    {
        para = va_arg( argp, char*);
        if (strcmp(para, "") == 0 )
            break;
        printf("Parameter #%d is: %s\n", argno, para);
        argno++;
    }
    va_end( argp );
    return 0;
}

int vspf(char *fmt, ...)
{
    int cnt;
    va_list ap;
    printf("%s \n", fmt);

    va_start(ap, fmt);
    printf("%s \n", fmt);
    printf("%s \n", (char*)ap);
    cnt = vsnprintf(buffer,bufsize ,fmt, ap);
    va_end(ap);

    return(cnt);
}

