#include <stdio.h>
#include "sum.h"

int sum(int a, int b){
    printf("in add\n");
    return a+b;
}

char* stringSum(int a, char b, char* c){
    char result[32];
    snprintf(result, sizeof(result), "%d,%c,%s",a,b,c);
    return result;
}

//gcc add.c -o libadd.so -fPIC -shared -I../include