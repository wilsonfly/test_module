#include <stdio.h>
#include "add.h"

int add(int a, int b){
    printf("in add\n");
    return a+b;
}

//gcc add.c -o libadd.so -fPIC -shared -I../include