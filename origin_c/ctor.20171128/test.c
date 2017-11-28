#include <stdio.h>
#include <string.h>
#include "llog_priv.h"

void myInit(void)
{
    printf("hello ");
}


#if 1
//rule no1
typedef void (*pCtor)(void);
pCtor __attribute__ ((section (".ctors"))) my_init_p = &myInit;
#else
//rule no2, a better one
void myInit(void) __attribute__ ((constructor));
#endif


int main()
{
    printf("world\n");
//	log();
}
