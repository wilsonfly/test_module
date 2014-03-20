#include <stdio.h>
#include "llog_priv.h"

int main()
{
	int a[3];
	int p = sizeof(&a);
	log("a:0x%p,&a:0x%p,p:%d",a,&a,p);
}
