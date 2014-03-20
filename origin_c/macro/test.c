
#include <stdio.h>
#include "llog_priv.h"
#define MACRO macro

typedef struct _num{
	int a;
	int macro;
}num_t;

int main()
{
	num_t num;
	num.a=1;
	num.MACRO=2;
	log("num.a:%d,num.macro:%d,num.MACRO:%d", num.a, num.macro, num.MACRO);
}
