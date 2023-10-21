#include <stdio.h>
#include "llog_priv.h"

int global_init_var = 84;
int global_uninit_var;

void func(int i)
{
    printf("%d\n",i);
}

int main()
{
    static int static_var = 85;
    static int static_var2;
    int a = 1;
    int b;

    func( static_var + static_var2 + a + b );

    printf("helloooooo\n");
	log();

    int array[4];
    int i = 0;
    array[i] = i++;

    int c,d,e,f;
    c=d=e=f=0;
    c?d=e:f;

    return a;
}
