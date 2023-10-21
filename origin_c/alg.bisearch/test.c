#include <stdio.h>
#include "llog_priv.h"

int biSearch(int data[], int size, int target)
{
    int mid = 0;
    int left = 0;
    int right = size-1;

    while(left <= right)
    {
        //mid = (left + right)/2;
        mid = (left + right)>>1;
        printf("left:%d,right:%d,mid:%d\n",left, right, mid);
        if(data[mid] > target)
            right = mid-1;
        else if(data[mid] < target)
            left = mid+1;//如果没有+1,可能死循环,比如最大14，在13,14时计算mid得13,到不了14
        else if(data[mid] == target)
            return mid;
    }
    return -1;
}


void show(int data[], int size)
{
    int i;
    for(i=0;i<size;i++)
    {
        printf("[%d]%d,",i,data[i]);
    }
    printf("\n");

}

int main()
{
    //int data[] = {1,3,5,7,8,9,10,13,15,18,22,30,33,38,50};
    int data[] = {1,3,5,7,8,9,10,13,15,18,22,30,33,38};
    int size = sizeof(data)/sizeof(int);

    show(data,size);
    printf("input one num to search:\n");

    int num;
    int ret;
    scanf("%d",&num);
    ret = biSearch(data, size, num);
    printf("index:%d\n",ret);
}
