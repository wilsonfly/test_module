#include <stdio.h>
#include <stdbool.h>

/*
给出整数n,求小于n的质数的个数

分析：
 要求输出小于输入数的质数的个数，可以使用筛选法（埃拉托斯特尼筛法）进行求解。首先，我们创建一个长度为n的布尔数组 isPrime，并初始化全部元素为true。
 接着，从2开始遍历，对于每个质数，将其所有的倍数标记为非质数（设置为false）。最后，统计数组中值为true的个数即可得到小于输入数的质数的个数。
 */
int countPrimes(int num) {
    int i,j;
    bool isPrime[num]; // 用于记录每个数是否为质数

    for(i = 0; i < num; ++i) {
        isPrime[i] = true;
    }
    
    int count = 0;
    
    for(i = 2; i < num; ++i) {//1和num本身不计算在内
        if (isPrime[i]) {
            count++;
            
            // 将质数i的所有倍数标记为非质数
            for (j = i + i; j < num; j += i) {
                isPrime[j] = false;
            }
        }
    }
    
    return count;
}


#if 0
#include <math.h>
int countPrimes2(int num) {
    int i,j;
    int count=0;

    int ret = sqrt(num);
    printf("sqrt %d = %d \n",num, ret);

    return count;
}
#endif

int main() {
    int num;
    printf("Enter a number: ");
    scanf("%d", &num);
    
    int result = countPrimes(num);
    
    printf("Number of primes: %d\n", result);

    return 0;
}
