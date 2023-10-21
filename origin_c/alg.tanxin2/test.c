#include <stdio.h>
#include <string.h>

void show(int array[], int size)
{
    int i;
    for(i=0;i<size;i++)
    {
        printf("%d,",array[i]);
    }
    printf("\n");
}

/*
题目描述
一群孩子站成一排，每一个孩子有自己的评分。现在需要给这些孩子发糖果，规则是如果一
个孩子的评分比自己身旁的一个孩子要高，那么这个孩子就必须得到比身旁孩子更多的糖果；所
有孩子至少要有一个糖果。求解最少需要多少个糖果。
输入输出样例
输入是一个数组，表示孩子的评分。输出是最少糖果的数量。
Input: [1,0,2]
Output: 5
在这个样例中，最少的糖果分法是[2,1,2]。
 */
/*
题解：
把所有孩子的糖果数初始化为1；
先从左往右遍历一遍，如果右边孩子的评分比左边的高，则右边孩子的糖果数更新为左边孩子的
糖果数加1；再从右往左遍历一遍，如果左边孩子的评分比右边的高，且左边孩子当前的糖果数
不大于右边孩子的糖果数，则左边孩子的糖果数更新为右边孩子的糖果数加1。通过这两次遍历，
分配的糖果就可以满足题目要求了。这里的贪心策略即为，在每次遍历中，只考虑并更新相邻一
侧的大小关系。
在样例中，我们初始化糖果分配为[1,1,1]，第一次遍历更新后的结果为[1,1,2]，第二次遍历
更新后的结果为[2,1,2]。
 */
int findMinCandy(int rating[], int size)
{
    int i;
    int candy[size];
    int count = 0;

    //首次赋初始值
    candy[0] = 1;

    //从左向右遍历，如果右边评分比左侧孩子评分高，右侧+1
    for(i=1; i<size; i++)
    {
        //首次赋初始值
        candy[i] = 1;

        if(rating[i-1] < rating[i])
        {
            //candy[i]++; //also ok
            candy[i] = candy[i-1]+1;
        }
    }

    for(i=size-1; i>=1; i--)
    {
        if((rating[i-1]>rating[i]) && (candy[i-1]<=candy[i]))
        {
            candy[i-1] = candy[i]+1;
        }
        count += candy[i];
    }
    count += candy[0];

    show(candy,size);
    return count;

}

/*
1,0,2,
2,1,2,
5

3,1,1,0,3,
2,1,2,1,2,
8

 */
int main()
{
    //int rating[] = {1,0,2};
    int rating[] = {3,1,1,0,3};
    int ret = 0;
    int size = sizeof(rating)/sizeof(int);

    show(rating,size);
    ret = findMinCandy(rating,size);
    printf("%d\n",ret);

}
