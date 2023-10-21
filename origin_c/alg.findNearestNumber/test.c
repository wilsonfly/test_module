#include <stdio.h>
/*
给出一个正整数，找出这个正整数所有数字全排列的下一个数。
说通俗点就是在一个整数所包含数字的全部组合中，找到一个大于且仅大于原
数的新整数。让我们举几个例子。
如果输入12345，则返回12354。
如果输入12354，则返回12435。
 */


//从后向前查看逆序区域，找到逆序区域的边界，也就是数字置换的边界
//从右向左找到第一个大于左边数字的位置,比如12345的5, 12354的5, 123654的6, 123650的6
int findTransferBorder(int data[], int size){
    int i;
    for(i=size-1; i>0; i--)
    {
        if(data[i] > data[i-1])
        {
            return i;
        }
    }
    return 0;
}

//逆序边界的前一个index-1, 从逆序中从右向左找到第一个大于大的值进行交换;比如12345的45, 12354的34, 123654的34, 123650的35, 123611的36;
void exchangeHead(int data[], int size, int index){
    int i;
    int head = data[index-1];
    for(i=size-1; i>0; i--)
    {
        if(head < data[i])
        {
            data[index-1] = data[i];
            data[i] = head;
            break;
        }
    }
    return;
}

//12354->12453--reverse->12435
//交换index之后的逆序区域,即变为顺序
void reverseInverseArea(int data[], int size, int index){
    int i,j,tmp;
    for(i=index,j=size-1; i<j; i++,j--){
        tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
    return;
}

void findNearestNumber(int data[], int size)
{
    int index = findTransferBorder(data, size);

    // 如果数字置换边界是0，说明整个数组已经逆序，无法得到更大的相同数字组成的整数
    if(index == 0)
        return;

    exchangeHead(data, size, index);

    reverseInverseArea(data, size, index);
    return;
}

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
1,2,3,4,5,
1,2,3,5,4,

1,2,3,5,4,
1,2,4,3,5,

1,2,3,6,5,4,
1,2,4,3,5,6,
 */

int main() {
    //int data[] = {1,2,3,4,5};
    //int data[] = {1,2,3,5,4};
    int data[] = {1,2,3,6,5,4};
    int size = sizeof(data)/sizeof(int);

    show(data,size);
    findNearestNumber(data, size);
    show(data,size);
}

