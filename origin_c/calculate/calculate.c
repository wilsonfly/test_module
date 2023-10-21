
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
/*
 * 练习1000以内四则运算的工具
 */

void showUsage();

int main()
{
    char option,alg;
    double num1,num2,target,result;
    char strResult[16];

    showUsage();

    while(1) {
        scanf("%c",&option);
        if(option!='1' && option!='2' && option!='3' && option!='4' && option!='q') {
            printf("输入有误，请重新输入\n");
        } else {
            if(option=='q') {
                return 0;
            } else if(option=='1') {
                alg = '+';
            } else if(option=='2') {
                alg = '-';
            } else if(option=='3') {
                alg = '*';
            } else if(option=='4') {
                alg = '/';
            }
            break;
        }
    }

    srand(time(NULL));
    num1=rand()%1001;
    num2=rand()%1001;
    printf("\n请计算：\n%lf%c%lf=\n",num1,alg,num2);
    printf("\n请输入结果:(保留两位小数)\n");
    scanf("%lf",&target);
    if(alg=='+')
        result=num1+num2;
    else if(alg=='-')
        result=num1-num2;
    else if(alg=='*')
        result=num1*num2;
    else if(alg=='/') {
        result=num1/num2;

        //保留两位小数
        sprintf(strResult,"%.2f",result);
        result = atof(strResult);
    }

    if(target==result)
        printf("恭喜，计算结果正确\n");
    else
        printf("抱歉，计算结果不正确，答案是%.2f\n",result);
    return 0;

}

void showUsage() {
    printf("请选择四则运算中的一种运算:\n");
    printf("\t1.加法\n");
    printf("\t2.减法\n");
    printf("\t3.乘法\n");
    printf("\t4.除法\n");
    printf("\tq.退出\n");
}
