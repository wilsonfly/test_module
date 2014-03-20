#include <stdio.h>
#include <string.h>
#include "llog_priv.h"

int main()
{
	char* buf = "A buf to test strstr\n";
	char* p = "test";
	char* ret;

	ret = strstr(buf,p);
	if( ret )
		log("ret:%s\n", ret);
	else
		log("ret:%s\n", ret);

	sleep(3);

	//Segmentation fault
	p = NULL;
	ret = strstr(buf,p);
	if( ret )
		log("ret:%s\n", ret);
	else
		log("ret:%s\n", ret);

}
