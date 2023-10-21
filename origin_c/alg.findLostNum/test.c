#include <stdio.h>

/*
假设一个无序数组里有若干个正整数，范围是1～100，其中有98个整数出现了
偶数次，只有2个整数出现了奇数次，如何找到这2个出现奇数次的整数？
 */
void findLostNum(int data[], int size, int result[]) {
    //第1次进行整体异或运算
    int xorResult = 0;
    int i;
    for(i=0;i<size;i++){
        xorResult^=data[i];
    }
    printf("xorResult:%d\n",xorResult);
    //如果进行异或运算的结果为0，则说明输入的数组不符合题目要求
    if(xorResult == 0){
        printf("data invalid\n");
        return ;
    }

    //确定2个整数的不同位，以此来做分组
    //比如数组中3和5出现奇数次, xorResult=0110b=6, 倒数第二位的1说明3和5在此位数上不同，因此可据此分组
    int separator = 1;
    while (0 == (xorResult & separator)){
        separator <<= 1;
    }
    printf("separator:%d\n",separator);
    //第2次分组进行异或运算
    for(i=0; i<size; i++){
        if(0 == (data[i] & separator)){
            result[0]^=data[i];
        }else {
            result[1]^=data[i];
        }
    }

    printf("%d,%d\n",result[0],result[1]);
    return;
}

/*
一个无序数组里有若干个正整数，范围是1～100，其中99个整数都出现了偶数
次，只有1个整数出现了奇数次，如何找到这个出现奇数次的整数？
 */
int findLostNum0(int data[], int size)
{
    int i;
    int ret = 0;
    for(i=0; i<size; i++)
    {
        ret ^= data[i];
    }
    return ret;
}

int main() {
    int data[] = {4,1,2,2,5,1,4,3};
    int result[2] = {};//需要初始化，要不然又垃圾数据
    int size = sizeof(data)/sizeof(int);
    
    findLostNum(data, size, result);
    printf("result:%d,%d\n",result[0],result[1]);


    int data0[] = {4,1,2,2,5,1,4,3,3};
    int size0 = sizeof(data0)/sizeof(int);
    int ret0;

    ret0 = findLostNum0(data0, size0);
    printf("findLostNum0:%d\n",ret0);
}
