#include <stdio.h>
#include "llog_priv.h"

int main()
{
	const int a = 9;
	int *p = (int*)&a;
	*p = 90;
	log("a:%d,*p:%d",a,*p);
}
