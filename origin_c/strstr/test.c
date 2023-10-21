#include <stdio.h>
#include <string.h>
#include "llog_priv.h"

int main()
{
	//char* buf = "huasheng,a buf to test strstr\n";
	//char* p = "test";
	char* ret;
	
    char buf[32] = "43Y1S";
	//char* buf = "43Y1S-Edge";
	char* p = "-Edge";

	ret = strstr(buf,p);
	if( ret )
		log("true,ret:%s\n", ret);
	else
		log("false,ret:%s\n", ret);

    /*
    if( strstr(buf,"huasheng") )
        log("true, get huasheng in buf");
    else
        log("false, not get huasheng in buf");

    if( strstr(buf,"abc`") )
        log("true, get abc in buf");
    else
        log("false, not get abc in buf");

	sleep(1);

	//Segmentation fault
	p = NULL;
	//ret = strstr(buf,p);
	if( ret )
		log("true,ret:%s\n", ret);
	else
		log("false,ret:%s\n", ret);
    */

}
