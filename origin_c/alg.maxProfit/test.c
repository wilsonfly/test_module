#include <stdio.h>

/*
给定一段时间内每天的股票价格，已知你只可以买卖各一次，求最大的收益。
 */

//使用了递归
#define MAX(x,y) (x)>(y)?(x):(y)
#define MIN(x,y) (x)<(y)?(x):(y)
int maxProfit(int* data, int startIndex, int endIndex)
{
    if(startIndex >= endIndex)
        return 0;
    if(startIndex ==0 && endIndex==1)
        return MAX(data[1]-data[0],0);//0买1卖,或者不买卖

    int num1=0, num2=0;
    int buy = data[startIndex];
    int i,profit;

    //index不卖，从后往前分析
    //num1 = maxProfit(data,startIndex,endIndex-1);
    
    //index不买，从前往后分析
    num1 = maxProfit(data,startIndex+1,endIndex);

    //index买
    for(i=startIndex+1; i<=endIndex; i++)
    {
        profit = data[i] - data[startIndex];
        if(profit > num2)
            num2 = profit;
    }

    return MAX(num1, num2);
}

//用数组保存当前价格与历史最低值差值，即截止当前最大收益
int maxProfit2(int data[], int size)
{
    int i;
    int minPrice = data[0];
    int maxProfit = 0;
    //int dp[size] = {0};//截止当前index，最大收益
    int* dp = (int*)malloc(size*sizeof(int));

    memset(dp, 0, size*sizeof(int));
    for(i=0; i<size; i++)
    {
        minPrice = MIN(minPrice, data[i]);
        dp[i] = data[i] - minPrice;
        maxProfit = MAX(dp[i],maxProfit);
    }
    return maxProfit;
}

//数组其实也没用上，用maxProfit存一个就行了
int maxProfit3(int data[], int size)
{
    int i;
    int minPrice = data[0];
    int maxProfit = 0;

    for(i=0; i<size; i++)
    {
        minPrice = MIN(minPrice, data[i]);
        maxProfit = MAX(data[i] - minPrice,maxProfit);
    }
    return maxProfit;
}

int main()
{
    //int data[] = {7,1,5,3,6,4};//5
    int data[] = {7,1,5,3,6,4,7};//6
    int size = sizeof(data)/sizeof(int);

    printf("maxprofit:%d\n",maxProfit(data,0,size-1));
    printf("maxprofit2:%d\n",maxProfit2(data,size));
    printf("maxprofit3:%d\n",maxProfit3(data,size));

}
