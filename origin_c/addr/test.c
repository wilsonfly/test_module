#include <stdio.h>
#include "llog_priv.h"

int main()
{
	char a[]="aaaaa!";
	char b[]="aaaaa!";
	if( a==b )
		log("a[] b[] equal");
	char *p = "dddddddddd";
	char *q = "dddddddddd";
	if( p==q )
		log("*p *q equal");
}
