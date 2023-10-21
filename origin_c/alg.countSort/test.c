#include <stdio.h>
#include <string.h>

void show(int data[], int size)
{
    int i;
    for(i=0;i<size;i++)
    {
        printf("%d,",data[i]);
    }
    printf("\n");

}

/*
计数排序
它适用于一定范围内的整数排序。在取值范围不是很大的情况下，它的性能甚至快过那些时间复杂度
为O(nlogn)的排序。
 */
void countSort(int data[],int size)
{
    //1.得到数列的最大值
    int i,j;
    int index = 0;
    int max = data[0];

    for(i=1; i<size; i++){
        if(data[i] > max){
            max = data[i];
        }
    }
    //2.根据数列最大值确定统计数组的长度
    int hash[max];
    memset(hash, 0, max*sizeof(int));//记得清零，否则有些乱数据

    //3.遍历数列，填充统计数组
    for(i=0; i<size; i++){
        hash[data[i]]++;
    }
    //show(hash,max);

    //4.遍历统计数组，输出结果
    for(i=0,index=0; i<max; i++)
    {
        for(j=0; j<hash[i]; j++){
            data[index++] = i;
        }
    }
}

void main() {
    int data[] = {4,4,6,5,3,2,8,1,7,5,6,0,10};
    int size = sizeof(data)/sizeof(int);

    show(data,size);
    countSort(data,size);
    show(data,size);
}
