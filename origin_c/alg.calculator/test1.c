#include <stdio.h>
#include <ctype.h>

//错误示范，没有优先级
int calculate(char *s) {
    int num = 0; // 当前数字
    char sign = '+'; // 当前运算符
    int res = 0; // 最终的计算结果

    while (*s) {
        if (isdigit(*s)) {
            num = num * 10 + (*s - '0'); // 多位数字处理
        }

        if (!isdigit(*s) && *s != ' ' || !*(s+1)) {
            if (sign == '+') {
                res += num;
            } else if (sign == '-') {
                res -= num;
            } else if (sign == '*') {
                res *= num;
            } else if (sign == '/') {
                res /= num;
            }

            num = 0; // 重置数字
            sign = *s; // 更新运算符
        }

        s++;
    }

    return res;
}

int main() {
    char s[] = " 3+5 / 2 ";
    printf("[%s] result is: %d\n",s,calculate(s));

    char s1[] = " 3+5 / 2 * 3 ";
    printf("[%s] result is: %d\n",s1,calculate(s1));

    return 0;
}
