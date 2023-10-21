#include <stdio.h>
#include <string.h>//未包含此头文件会段错误
//#include "llog_priv.h"

int main()
{
	//log();

#define INFO_MAX_SZ 64
    int in=0;  
    char buffer[INFO_MAX_SZ]="Fred male 25,John male 62,Anna female 16";      
    char *p[20];
    char *buf = buffer;
    typedef struct person{ 
        char name[25];
        char sex[10];
        char age[4];
    }Person;
    int num;

#if 0
    p[in] = strtok(buf," ,");
    //while((p[in] = strtok(buf," ,")) != NULL)
    while(p[in] != NULL)
    {
        printf("in:%d\n",in);
        switch(in%3)
        {
            case 0:
                printf("name:%s\n",p[in]);
                break;
            case 1:
                printf("sex:%s\n",p[in]);
                break;
            case 2:
                printf("age:%s\n",p[in]);
                break;
        }
        in++;
        p[in] = strtok(NULL," ,");
        //buf = NULL;
    }
#endif
#if 0
    while((p[in] = strtok(buf," ,")) != NULL)
    {
        printf("in:%d\n",in);
        switch(in%3)
        {
            case 0:
                printf("name:%s\n",p[in]);
                break;
            case 1:
                printf("sex:%s\n",p[in]);
                break;
            case 2:
                printf("age:%s\n",p[in]);
                break;
        }
        in++;
        buf = NULL;
    }
#endif
#if 0
    char* p_save;
    while((p[in] = strtok_r(buf," ,",&p_save)) != NULL)
    {
        printf("in:%d\n",in);
        switch(in%3)
        {
            case 0:
                printf("name:%s\n",p[in]);
                break;
            case 1:
                printf("sex:%s\n",p[in]);
                break;
            case 2:
                printf("age:%s\n",p[in]);
                break;
        }
        in++;
        buf = NULL;
    }
#endif
#if 1
    char* p_out;
    char* p_in;
    while((p[in] = strtok_r(buf,",",&p_out)) != NULL)
    {
        buf=p[in];
        while((p[in] = strtok_r(buf," ",&p_in)) != NULL)
        {
            in++;
            buf = NULL;
        }

        buf = NULL;
    }
#endif
    
    //if((in+1)%3 != 0)//in此时为9
    if( in%3 != 0)
    {
        printf("data is dirty\n");
        return;
    }
    num = in/3;
    Person persons[num];
    for(in=0;in<num;in++)
    {
        strncpy(persons[in].name,p[in*3 + 0],sizeof(persons[in].name));
        strncpy(persons[in].sex,p[in*3 + 1],sizeof(persons[in].sex));
        strncpy(persons[in].age,p[in*3 + 2],sizeof(persons[in].age));
        printf("persons[%d],name:%s,sex:%s,age:%s\n",in,persons[in].name, persons[in].sex, persons[in].age);
    }
}
