#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int getPriority(char c)
{
    if (c == '*' || c == '/')
        return 2;
    else if (c == '+' || c == '-')
        return 1;
    return 0;
}

int calculate(char* str) {
    int len = strlen(str);
    int* numStack = (int*)malloc(len * sizeof(int)); // 用于存储数字的栈
    char* opStack = (char*)malloc(len * sizeof(char)); // 用于存储运算符的栈
    int numTop = -1; // 栈顶指针
    int opTop = -1; // 栈顶指针

    int num = 0; // 存储当前数字
    int i;

    // 遍历字符串
    for (i = 0; i < len; i++)
    {
        if (isdigit(str[i]))
            num = num * 10 + (str[i] - '0');

        // 如果遇到运算符或者字符串末尾时进行计算
        if ((!isdigit(str[i]) && str[i] != ' ') || i == len - 1) {
            numStack[++numTop] = num;
            num = 0;

            //printf("new op:%c,numStack[numTop]:%d,opStack[opTop]:%c\n",str[i],numStack[numTop],opStack[opTop]);
            while (opTop >= 0 && getPriority(opStack[opTop]) >= getPriority(str[i]))
            {
                int b = numStack[numTop--];
                int a = numStack[numTop--];
                char op = opStack[opTop--];
                //printf("b:%d,a:%d,op:%c\n",b,a,op);

                if (op == '+') {
                    numStack[++numTop] = a + b;
                } else if (op == '-') {
                    numStack[++numTop] = a - b;
                } else if (op == '*') {
                    numStack[++numTop] = a * b;
                } else if (op == '/') {
                    numStack[++numTop] = a / b;
                }
            }

            if (i != len-1)//如果是i到尾部而进入的if代码块,这里不能做操作符入栈
            {
                opStack[++opTop] = str[i];
                //printf("new op push stack:%c,numStack[numTop]:%d\n",str[i],numStack[numTop]);
            }
        }
    }

    int result = numStack[numTop];
    free(numStack);
    free(opStack);

    return result;
}

//202307
int calculate2(char* str)
{
    int len = strlen(str);
    int numTop=-1,opTop=-1;//初始值
    int* numStack = (int*)malloc(len*sizeof(int));
    char* opStack = (char*)malloc(len*sizeof(char));
    int i;
    int num=0,result=0;

    for(i=0; i<len; i++)
    {
        if(isdigit(str[i]))
            num = num*10 + (str[i]-'0');

        //遇到运算符或者 到结尾了，运算符进栈或者进行计算
        if( (!isdigit(str[i]) && str[i]!= ' ') || i==len-1)
        {
            numStack[++numTop] = num;
            num = 0;

            //运算符栈顶有运算符 并且优先级大于等于 当前遇到的运算符, 数据栈出栈计算
            //if(opTop>=0 && getPriority(opStack[opTop])>= getPriority(str[i]) )//计算完也需要入栈，所以不用单独区分是否要进行计算
            while(opTop>= 0 && getPriority(opStack[opTop])>= getPriority(str[i]))//可能是结尾(数字or空格),此时str[i]是数据,也返回个优先级进行比较
            {
                int b = numStack[numTop--];
                int a = numStack[numTop--];
                char op = opStack[opTop--];

                if(op == '+')
                    numStack[++numTop] = a+b;
                else if(op == '-')
                    numStack[++numTop] = a-b;
                else if(op == '*')
                    numStack[++numTop] = a*b;
                else if(op == '/')
                    numStack[++numTop] = a/b;
            }

            if(i != len-1)
                opStack[++opTop] = str[i];//如果不是结尾进入的if，说明str[i]是运算符，入栈

        }
    }

    result = numStack[numTop];
    free(numStack);
    free(opStack);

    return result;
}

/*
[ 3+5 ] result is: 8
[ 3+5 / 2 ] result is: 5
[ 3+5 / 2 * 3 ] result is: 9
[ 3+5 / 2 * 33] result is: 69
[ 3*5 + 2 * 3 ] result is: 21
[ 3*15 + 2 * 3 ] result is: 51
 */
int main()
{
    char s[] = " 3+5 / 2 ";
    printf("[%s] result is: %d\n",s,calculate(s));

    char s1[] = " 3+5 / 2 * 3 ";
    printf("[%s] result is: %d\n",s1,calculate(s1));

    char s2[] = " 3+5 / 2 * 33";
    printf("[%s] result is: %d\n",s2,calculate(s2));

    char s3[] = " 3*5 + 2 * 3 ";
    printf("[%s] result is: %d\n",s3,calculate(s3));

    char s4[] = " 3*15 + 2 * 3 ";
    printf("[%s] result is: %d\n",s4,calculate(s4));

    printf("=======================================\n");

    char s5[] = " 3+5 / 2 ";
    printf("[%s] result is: %d\n",s5,calculate2(s5));

    char s6[] = " 3+5 / 2 * 3 ";
    printf("[%s] result is: %d\n",s6,calculate2(s6));

    char s7[] = " 3+5 / 2 * 33";
    printf("[%s] result is: %d\n",s7,calculate2(s7));

    char s8[] = " 3*5 + 2 * 3 ";
    printf("[%s] result is: %d\n",s8,calculate2(s8));

    char s9[] = " 3*15 + 2 * 3 ";
    printf("[%s] result is: %d\n",s9,calculate2(s9));

    char s10[] = " 3 + 5 ";
    printf("[%s] result is: %d\n",s10,calculate2(s10));

    return 0;
}
