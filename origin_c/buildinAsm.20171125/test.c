//#include <stdio.h>
//#include "llog_priv.h"

/*
 * one demo from 程序员的自我修养
 */

char* str = "hello world\n";

void myPrint()
{
    asm("movl $13, %%edx \n"
            "movl %0,%%ecx \n"
//            "movl $0,%%ebx \n"
            "movl $1,%%ebx \n" //stdin:0  stdout:1 stderr:2
            "movl $4,%%eax \n"
            "int $0x80 \n"
            ::"r"(str):"edx","ecx","ebx");
}

void myExit()
{
    asm("movl $42,%ebx \n"
            "movl $1,%eax \n"
            "int $0x80 \n" );
}

void myMain()
{
    myPrint();
    myExit();
}
