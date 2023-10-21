#include <stdio.h>

/*
Given a sorted array, remove the duplicates in place such that each element appear only once
and return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.
For example, Given input array A = [1,1,2],
Your function should return length = 2, and A is now [1,2].
 */

int removeDup(int data[], int size)
{
    int index,i,j;

    for(i=0,j=0; i+1<size; )
    {
        if(data[i]==data[i+1])
        {
            index = i+1;
            i+=2;
            j++;
        }
        else
        {
            i++;
            j++;
        }
        data[j] = data[i];
    }
    data[size-1] = 0;//填充最后一位

    return index;
}

/*
Follow up for ”Remove Duplicates”: What if duplicates are allowed at most twice?
For example, Given sorted array A = [1,1,1,2,2,3],
Your function should return length = 5, and A is now [1,1,2,2,3]
 */
void removeDup2(int data[], int size)
{
    int i,j;

    for(i=1,j=1; i<size; )
    {
        if(data[j] != data[j-1])
        {
            j++;
        }
        i++;
        data[j] = data[i];
    }
    //填充最后
    for(j;j<size;j++)
        data[j] = 0;

}

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
1,1,2,3,5,7,
dup index:1
1,2,3,5,7,0,

1,2,2,3,5,7,
dup index:2
1,2,3,5,7,0,

1,2,3,5,7,7,
dup index:5
1,2,3,5,7,0,

 */
int main()
{
    //int data[] = {1,1,2,3,5,7};
    //int data[] = {1,2,2,3,5,7};
    int data[] = {1,2,3,5,7,7};
    int size = sizeof(data)/sizeof(int);

    int index;
    
    show(data, size);
    index = removeDup(data,size);
    printf("dup index:%d\n",index);
    show(data, size);


    /*
1,1,1,2,2,3,
1,2,3,0,0,0,

1,1,2,2,3,
1,2,3,0,0,

     */
    int data2[] = {1,1,1,2,2,3};
    //int data2[] = {1,1,2,2,3};
    int size2 = sizeof(data2)/sizeof(int);
    
    show(data2, size2);
    removeDup2(data2, size2);
    show(data2, size2);

}
