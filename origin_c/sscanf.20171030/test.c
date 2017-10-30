#include <stdio.h>
#include "llog_priv.h"

int main()
{
    int ret = 0;
    char* str1 = "6f000000-6f01e000 rwxp 00000000 00:0c 16389419   /system/lib/libcomposer.so\n";
    //char* str1 = "000000-6f01e000 rwxp 6f01e0aa 00:0c 16389419   /system/lib/libcomposer.so\n";
    unsigned long int start;
    unsigned long int end;
    unsigned long int num1;
    unsigned long int num2;
    unsigned int min;
    unsigned int sec;
    char permissions[5];
    int name_pos;

    ret = sscanf(str1,"%lx-%lx %4s %*x %*x:%*x %*d %n", &start, &end, permissions, &name_pos);
	log("ret:%d", ret);

	log("start:%lx", start);
	log("end:%lx", end);
	log("permissions:%s", permissions);
	log("name_pos:%d", name_pos);

    //%*x means ignore one %x number!!
    //ret = sscanf(str1,"%lx-%lx %4s %*x %*x:%*x %*d %n", &start, &end, permissions, &num1, &min, &sec, &name_pos);
    ret = sscanf(str1,"%lx-%lx %4s %lx %x:%x %*d %n", &start, &end, permissions, &num1, &min, &sec, &name_pos);
	log("ret:%d", ret);

	log("start:%lx", start);
	log("end:%lx", end);
	log("permissions:%s", permissions);
	log("num1:%lx", num1);
	log("min:%02x", min);
	log("sec:%02x", sec);
	log("name_pos:%d", name_pos);
}
