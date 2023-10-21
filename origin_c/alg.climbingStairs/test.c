#include <stdio.h>

//递归方式
int climbStairs(int num)
{
    if(num < 0)
        return -1;

    if(num <= 2)
        return num;

#if 0//这个结束条件导致 0,1,1,2分别对应0,1,2,3阶,是不对的
    if(num == 0)
        return 0;

    if(num == 1)
        return 1;
#endif

#if 0
    int ret1 = 0;
    int ret2 = 0;
    //最后一台阶用一步
    ret1 = 1 + climbStairs(num-1);
    
    //最后一台阶用两步
    ret2 = 1 + climbStairs(num-2);
    return ret1+ret2;
#endif

    return climbStairs(num-1) + climbStairs(num-2);

}

//已知是斐波那契
int climbStairs2(int num)
{
    if(num < 0)
        return -1;

    if(num <= 2)
        return num;

    int i;
    int a = 1;
    int b = 2;
    int c;
    for(i=3; i<=num; i++)
    {
        c = a + b;
        a = b;
        b = c;
    }

    return c;
}

int main()
{
    printf("%d stairs,%d ways \n",1,climbStairs(1));
    printf("%d stairs,%d ways \n",2,climbStairs(2));
    printf("%d stairs,%d ways \n",3,climbStairs(3));
    printf("%d stairs,%d ways \n",4,climbStairs(4));
    printf("%d stairs,%d ways \n",5,climbStairs(5));
    printf("%d stairs,%d ways \n",6,climbStairs(6));

    printf("%d Stairs2,%d ways \n",1,climbStairs2(1));
    printf("%d Stairs2,%d ways \n",2,climbStairs2(2));
    printf("%d Stairs2,%d ways \n",3,climbStairs2(3));
    printf("%d Stairs2,%d ways \n",4,climbStairs2(4));
    printf("%d Stairs2,%d ways \n",5,climbStairs2(5));
    printf("%d Stairs2,%d ways \n",6,climbStairs2(6));
}
