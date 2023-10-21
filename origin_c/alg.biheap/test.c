#include <stdio.h>
//#include "llog_priv.h"

//推导1：假设父节点的下标是parent，那么它的左孩子下标就是 2×parent+1；右孩子
//下标就是2×parent+2。
//推导2：最后一个叶子节点的父节点，parent=(child-1)/2

/**
 * 上浮调整
 */
void upAdjust(int array[], int size)
{
    int childIndex = size-1;
    int parentIndex = (childIndex-1)/2;//按左子节点公式计算?? 奇数为左节点、偶数为右子节点
    // temp 保存插入的叶子节点值，用于最后的赋值
    int temp = array[childIndex];
    while (childIndex > 0 && temp < array[parentIndex])
    {
        //无须真正交换，单向赋值即可
        array[childIndex] = array[parentIndex];
        childIndex = parentIndex;
        parentIndex = (parentIndex-1) / 2;
    }
    array[childIndex] = temp;
}


#if 0
/**
 * “下沉”调整
 * @param array 待调整的堆
 * @param parentIndex 要“下沉”的父节点
 * @param size 堆的有效大小
 */
void downAdjust(int array[], int parentIndex, int size)
{
    // temp 保存父节点值，用于最后的赋值
    int temp = array[parentIndex];
    int childIndex = 2 * parentIndex + 1;//计算左子节点
    while (childIndex < size) {
        // 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
        if (childIndex + 1 < size && array[childIndex + 1] < array[childIndex]) {
            childIndex++;
        }
        // 如果父节点小于任何一个孩子的值，则直接跳出
        if (temp <= array[childIndex])
            break;
        //无须真正交换，单向赋值即可
        array[parentIndex] = array[childIndex];
        parentIndex = childIndex;
        childIndex = 2 * childIndex + 1;
    }
    array[parentIndex] = temp;
}
#endif

#if 1
//202307手写
void downAdjust(int array[], int parentIndex, int size)
{
    int childIndex = 2*parentIndex+1;
    int temp = array[parentIndex];
    
    while(childIndex < size)
    {
        if(childIndex+1 <size && array[childIndex]>array[childIndex+1])//???
            childIndex++;

        if(temp <= array[childIndex])
            break;

        array[parentIndex] = array[childIndex];
        parentIndex = childIndex;
        childIndex = 2*parentIndex+1;
    }
    array[parentIndex] = temp;
}
#endif

/**
 * 构建堆
 * @param array 待调整的堆
 */
void buildHeap(int array[], int size)
{
    int i;
    // 从最后一个非叶子节点开始，依次做“下沉”调整
    for (i = (size-2)/2; i>=0; i--) { //按照最后一个子节点是右子节点计算的??//最后节点index:size-1,父节点(size-1 - 1)/2
        downAdjust(array, i, size);
    }
}

void show(int data[], int size)
{
    int i;
    for(i=0;i<size;i++)
    {
        printf("%d  ",data[i]);
    }
    printf("\n");

}
/*
./sample_test
1  3  2  6  5  7  8  9  10  0
0  1  2  6  3  7  8  9  10  5
7  1  3  10  5  2  8  9  6
1  5  2  6  7  3  8  9  10

            1
      3         2
    6    5    7    8
  9  10 0
after upAdjust 
            0
      1   .      2
    6    3     7    8
  9  10 5
 */
void main() {
    int array[] = {1,3,2,6,5,7,8,9,10,0};
    int size = sizeof(array)/sizeof(int);
    int i;

    show(array,size);
    upAdjust(array, size);
    show(array,size);

    int array2[] = {7,1,3,10,5,2,8,9,6};
    int size2 = sizeof(array2)/sizeof(int);

    show(array2, size2);
    buildHeap(array2, size2);
    show(array2, size2);
}
