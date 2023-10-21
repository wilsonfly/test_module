#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "llog_priv.h"

char* return_final_str()
{
    char* p = "hello";
    return p;
}

char* return_str_arr()
{
    char p[6];
    p[0] = 'a';
    p[1] = 'b';
    p[2] = 'c';
    return p;
}

char* return_arr()
{
    char p[6];
    p[0] = 0x74;
    p[1] = 0x72;
    p[2] = 0xb0;
    p[3] = 0x9b;
    p[4] = 0x4f;
    p[5] = 0x83;

    log("====in return_arr");
    log("p0:%02x",p[0]);
    log("p1:%02x",p[1]);
    log("p2:%02x",p[2]);
    log("p3:%02x",p[3]);
    log("p4:%02x",p[4]);
    log("p5:%02x",p[5]);
    log("====in return_arr");
    return p;
}

unsigned char* return_unsigned_arr()
{
    unsigned char p[6];
    p[0] = 0x74;
    p[1] = 0x72;
    p[2] = 0xb0;
    p[3] = 0x9b;
    p[4] = 0x4f;
    p[5] = 0x83;

    log("====in return_unsigned_arr");
    log("p0:%02x",p[0]);
    log("p1:%02x",p[1]);
    log("p2:%02x",p[2]);
    log("p3:%02x",p[3]);
    log("p4:%02x",p[4]);
    log("p5:%02x",p[5]);
    log("====in return_unsigned_arr");
    return p;
}

unsigned char* return_space()
{
    unsigned char* p = (char*)malloc(6);
    p[0] = 0x74;
    p[1] = 0x72;
    p[2] = 0xb0;
    p[3] = 0x9b;
    p[4] = 0x4f;
    p[5] = 0x83;

    log("====in return_space");
    log("p0:%02x",p[0]);
    log("p1:%02x",p[1]);
    log("p2:%02x",p[2]);
    log("p3:%02x",p[3]);
    log("p4:%02x",p[4]);
    log("p5:%02x",p[5]);
    log("====in return_space");
    return p;
}

char* test_null_src()
{
    char* p = (char*)malloc(3);
    char* p1 = NULL;
    strncpy(p,p1,3);
    log("after strncpy null src:%s",p);
}

char* mystrcpy(char* dest,char* src)
{
    char* ret = dest;
    if(dest==NULL || src==NULL)
    {
        printf("invalid param\n");
        return NULL;
    }
    while( (*dest++ = *src++) != '\0')
    {
        ;
    }
    return ret;
}

void test_stack()
{
    //有的机器上会出现踩踏的情况
    char src[]="123456789";
    char dest[]="123";
    strcpy(dest,src);
    printf("test_stack,src:%s,dest:%s\n",src,dest);
}

void test_overlap()
{
    char dest[10];
    char* src="0123456789";
    strcpy(dest,src);
    printf("test_overlap,dest:%s\n",dest);
}

void test_overlap2()
{
    int i=0;
    char dest[10],src[10];

    for(i=0;i<10;i++)
        src[i]='a';

    strcpy(dest,src);
    printf("test_overlap2,dest:%s\n",dest);
}

void test_overlap3(char* src)
{
    //char tmp[];//test.c:138:10: error: array size missing in ‘tmp’
    char tmp[0];//ok
    char dest[10];

    if(strlen(src) <= 10)
        strcpy(dest,src);
    printf("test_overlap3,dest:%s\n",dest);
}

int main()
{
    char* p;
    p = return_final_str();
    log("final_str:%s",p);
    log("=====return_final_str===========\n");


    p = return_str_arr();
    log("str_arr:%s",p);
    log("=====return_str_arr===========\n");

    p = return_arr();
    log("strlen :%d",strlen(p));
    log("sizeof :%d",sizeof(p));
    log("p0:%02x",p[0]);
    log("p1:%02x",p[1]);
    log("p2:%02x",p[2]);
    log("p3:%02x",p[3]);
    log("p4:%02x",p[4]);
    log("p5:%02x",p[5]);
    log("=====rerun_arr===========\n");

    char* buf = (char*)malloc(6);
    memcpy(buf,return_arr(),6);
    log("strlen :%d",strlen(buf));
    log("sizeof :%d",sizeof(buf));
    log("buf0:%02x",buf[0]);
    log("buf1:%02x",buf[1]);
    log("buf2:%02x",buf[2]);
    log("buf3:%02x",buf[3]);
    log("buf4:%02x",buf[4]);
    log("buf5:%02x",buf[5]);
    if(buf != NULL)
    {
        free(buf);
    }
    log("=====rerun_arr===========\n");

    unsigned char* up = return_unsigned_arr();
    log("strlen :%d",strlen(up));
    log("sizeof :%d",sizeof(up));
    log("up0:%02x",up[0]);
    log("up1:%02x",up[1]);
    log("up2:%02x",up[2]);
    log("up3:%02x",up[3]);
    log("up4:%02x",up[4]);
    log("up5:%02x",up[5]);

    log("=====return_unsigned_arr===========\n");


    up = return_space();
    log("strlen :%d",strlen(up));
    log("sizeof :%d",sizeof(up));
    log("up0:%02x",up[0]);
    log("up1:%02x",up[1]);
    log("up2:%02x",up[2]);
    log("up3:%02x",up[3]);
    log("up4:%02x",up[4]);
    log("up5:%02x",up[5]);
    //if(up!=NULL && sizeof(up)==6)
    if(up!=NULL)
    {
        unsigned char* mac = (unsigned char*)malloc(17+1);
        memset(mac,0,17+1);
        //sprintf(mac,"%02x:%02x:%02x:%02x:%02x:%02x",up[0],up[1],up[2],up[3],up[4],up[5]);
        //snprintf(mac,17,"%02x:%02x:%02x:%02x:%02x:%02x",up[0],up[1],up[2],up[3],up[4],up[5]);
        //snprintf(mac,18,"%02x:%02x:%02x:%02x:%02x:%02x",up[0],up[1],up[2],up[3],up[4],up[5]);
        snprintf(mac,18,"%02X:%02X:%02X:%02X:%02X:%02X",up[0],up[1],up[2],up[3],up[4],up[5]);
        log("%s\n",mac);

        char* target = "74:72:b0:9b:4f:83";
        if( strncmp(mac,target,17) == 0)
        {
            log("----- equal");
        } else
        {
            log("----- not equal");
        }
        if(buf != NULL)
        {
            free(buf);
        }
    }
    log("=====return_space===========\n");
    
    //test_null_src();


    char dest[20];
    char src[]="hello world!";
    mystrcpy(dest,src);
    printf("after mystrcpy,dest:%s\n",dest);

    test_stack();
    log("=======test_stack over=========\n");

    test_overlap();
    test_overlap2();
    test_overlap3("0123456789");
    log("=======test_overlap over=========\n");

    log("=======over=========\n");
}
