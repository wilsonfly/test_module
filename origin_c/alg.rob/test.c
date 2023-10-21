#include <stdio.h>

/*
题目：强盗抢劫一排房间，每个房间都藏着一定数量的钱，不能抢劫两个相邻的房间，要求抢的钱最多

数组如：[2,7,9,3,1]
得12
 */
#define MAX(x,y) (x)>(y)?(x):(y)

//使用递归
int rob(int data[], int size)
{
    int num = 0;
    int num2 = 0;
    int max = 0;

    if(size <= 0)
        return 0;

    if(size == 1)
        return data[0];
    
    //不抢n,最大收益为rob(n-1);
    num = rob(data, size-1);

    //抢n，最大收益为data[n-1]+rob(n-2);
    num2 = data[size-1] + rob(data, size-2);
    //printf("size:%d,data[size-1]:%d,num:%d,num2:%d\n",size,data[size-1],num,num2);

    return MAX(num,num2);
}

//使用递归
//使用index，不用size
int robByIndex(int data[], int index)
{
    int num = 0;
    int num2 = 0;
    int max = 0;

    if(index == 0)
        return data[0];
    
    if(index == 1)
        return MAX(data[0],data[1]);
    
    //不抢index,最大收益为rob(index-1);
    num = robByIndex(data, index-1);

    //抢index，最大收益为data[index]+rob(index-2);
    num2 = data[index] + robByIndex(data, index-2);

    return MAX(num,num2);
}

//使用数组保存结果,不递归,从0,1->2 1,2->3, 2,3->4以此类推得到lastIndex的时候最优解
//时间复杂度O(n),空间复杂度也是O(n)
int robByDpArr(int data[], int lastIndex)
{
    if(lastIndex < 0)
        return 0;
    
    if(lastIndex == 0)
        return data[0];
    
    if(lastIndex == 1)
        return MAX(data[0],data[1]);
    
    int i;
    //int dp[size] = {0};//error: variable-sized object may not be initialized
    int *dp = (int*)malloc((lastIndex+1)*sizeof(int));

    memset(dp, 0, (lastIndex+1)*sizeof(int));
    dp[0] = data[0];
    dp[1] = MAX(data[0],data[1]);

    for(i=2; i<=lastIndex; i++)
    {
        dp[i] = MAX(dp[i-1], (dp[i-2]+data[i]));
        //printf("i:%d,%d\n",i,dp[i]);
    }

    return dp[lastIndex];
}

//不使用数组 也不递归,从0,1->2 1,2->3, 2,3->4以此类推得到lastIndex的时候最优解
//时间复杂度O(n),空间复杂度O(1)
int robByDp(int data[], int lastIndex)
{
    if(lastIndex <= 0)
        return 0;
    
    if(lastIndex == 0)
        return data[0];
    
    if(lastIndex == 1)
        return MAX(data[0],data[1]);

    //printf("data[0]:%d\n",data[0]);
    int i;
    int first,second,ret;

    first = data[0];
    second = MAX(data[0],data[1]);

    for(i=2; i<=lastIndex; i++)
    {
        //ret = MAX(m,n);//别忘了加上data[i]
        ret = MAX(second, (first+data[i]));
        first = second;
        second = ret;
    }

    return ret;
}


/*
问题升级：第一个房子和最后一个房子成了邻居，所以我们就不可以同时把他们两家一块儿抢了
 */
//递归
int roundRob1(int data[], int lastIndex)
{
    int num1,num2;

    //拆解成两个问题：
    //最后一个不抢：0~(index-1); 最后一个抢:1~(index)
    //两个范围内就变成简单的rob问题了
    num1 = robByDp(data,lastIndex-1);
    //num2 = robByDp(data+1,lastIndex);//错误,数据减少一个，最后一位数的index是lastIndex-1,
    num2 = robByDp(data+1,lastIndex-1);//取原数据的data[1]-data[lastIndex]这段数据

    return MAX(num1, num2);
}


int main()
{
    //int data[] = {2,7,9,3,1};//2,9,1 ->12
    int data[] = {2,7,10,3,1};//2,10,1 ->13
    //int data[] = {4,2,7,10,3,1};//4,10,1 ->15
    //int data[] = {2,7,10,3,1,5};//2,10,5->17
    int size = sizeof(data)/sizeof(int);

    printf("rob,ret:%d\n",rob(data,size));
    printf("rob2,ret:%d\n",robByIndex(data,size-1));
    printf("rob3,ret:%d\n",robByDpArr(data,size-1));
    printf("rob4,ret:%d\n",robByDp(data,size-1));
    

    printf("roundRob,ret:%d\n",roundRob1(data,size-1));

}
