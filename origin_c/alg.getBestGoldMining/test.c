#include <stdio.h>

/*
很久很久以前，有一位国王拥有5座金矿，每座金矿的黄金储量不同，需要参与
挖掘的工人人数也不同。例如有的金矿储量是500kg黄金，需要5个工人来挖掘；有
的金矿储量是200kg黄金，需要3个工人来挖掘……
如果参与挖矿的工人的总数是10。每座金矿要么全挖，要么不挖，不能派出一
半人挖取一半的金矿。要求用程序求出，要想得到尽可能多的黄金，应该选择挖取
哪几座金矿？
 */

/**
 * 获得金矿最优收益
 * @param w 工人数量
 * @param n 可选金矿数量
 * @param p 金矿开采所需的工人数量
 * @param g 金矿储量
 */
int getBestGoldMining(int w, int n, int p[], int g[])
{
    //边界问题
    //if(w==0 || n==0)
    if(w<=0 || n<=0)
        return 0;

#if 1
    //如果工人数不足以挖第n个矿,那最优解就是不挖n，即挖n-1
    if(w < p[n-1]){
        return getBestGoldMining(w, n-1, p, g);
    }
#endif
    
    //p[n-1]为挖第n个矿所需人数
    //g[n-1]为挖第n个矿的收益
    //不挖第n个矿
    int profit_undig_n = getBestGoldMining(w, n-1, p, g);
    //挖第n个矿
    int profit_dig_n = getBestGoldMining(w-p[n-1], n-1, p, g) + g[n-1];

    //取两种情况的最大值
    return profit_undig_n>profit_dig_n? profit_undig_n:profit_dig_n;
}

//202307
int getBestGoldMining2(int w, int n, int p[], int g[])
{
    if(w<=0 || n<=0)
        return 0;

    int profit_undig_n = 0;
    int profit_dig_n = 0;

    //不挖n
    profit_undig_n = getBestGoldMining(w, n-1, p, g);

    //挖n,但是前提是工人数得够，要不然默认加上g[n-1]是不对的;人数不够挖n当前最优解就是上面的不挖
    if(w >= p[n-1])
    {
        profit_dig_n = getBestGoldMining(w-p[n-1], n-1, p, g) + g[n-1];
    }
    return profit_undig_n>profit_dig_n? profit_undig_n:profit_dig_n;
}

int main() {
    int p[] = {5, 5, 3, 4, 3};
    int g[] = {400, 500, 200, 300, 350};

    //int p[] = {5, 3, 4, 3, 5};
    //int g[] = {500, 200, 300, 350, 400};
 
    //int p[] = {5, 3, 4 ,3, 5};
    //int g[] = {400, 200, 300, 350, 500};

    int w = 10;//10 workers
    int n = sizeof(p)/sizeof(int);//金矿数量
    int ret;

    ret = getBestGoldMining(w, n, p, g);//w=10, n=5
    printf("best result:%d\n",ret);
    
    ret = getBestGoldMining2(w, n, p, g);//w=10, n=5
    printf("best result:%d\n",ret);
}
