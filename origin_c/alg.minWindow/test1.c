#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
我们可以使用滑动窗口的方法来解决这个问题。首先，我们需要定义一个窗口，该窗口包含了t中的所有字符。
然后，我们可以通过移动窗口的左边界和右边界来寻找最短的子字符串。

具体步骤如下：

1.首先，我们需要统计字符串t中每个字符的出现次数，保存在一个哈希表中。
2.初始化左指针left和右指针right，分别指向s的起始位置。
3.初始化一个计数器count，用于记录窗口中已经包含了t中的字符的数量。
4.初始化最短子字符串的起始位置start和长度min_len，初始值为0和一个较大的数。
5.开始滑动窗口，通过移动右指针right来扩展窗口。每次移动时，更新窗口中字符的出现次数和计数器count。
6.当窗口中包含了t中的所有字符时，尝试移动左指针left来缩小窗口，同时更新最短子字符串的起始位置和长度。
7.重复步骤5和步骤6，直到右指针right到达字符串s的末尾。
8.返回最短子字符串。
 */
char* minWindow(char* s, char* t) {
    int s_len = strlen(s);
    int t_len = strlen(t);

    // 边界情况处理
    if (s_len < t_len || t_len == 0) return "";

    // 初始化哈希表和计数器
    int t_hash[128] = {0};
    int s_hash[128] = {0};
    int count = 0;
    int i;

    // 统计字符串t中每个字符的个数
    for (i = 0; i < t_len; i++) {
        t_hash[t[i]]++;
    }

    int left = 0, right = 0;
    int min_len = s_len + 1, start = 0;
    int window_len;

    printf("s_len:%d,t_len:%d\n",s_len,t_len);

    while (right < s_len) {
        // 右指针向右移动，更新窗口中字符的出现次数和计数器
        s_hash[s[right]]++;
        //if (s_hash[s[right]] <= t_hash[s[right]]) {
        if (s_hash[s[right]] <= t_hash[s[right]]) {
            count++;//count最大到t_len，当s中出现多次t中字符时count也不加
        }
        printf("s[right]:%c,right:%d,s_hash[%c]:%d,count:%d\n",s[right],right,s[right],s_hash[s[right]],count);

        // 当窗口中包含了t中的所有字符时，尝试移动左指针缩小窗口
        if (count == t_len) {
            //在已经找到一组t的情况下，right右移过程中再次出现t中字符,触发while
            //确切说是第一组第一个字符,比如s[9]即B不触发s[10]即A才触发
            while (s_hash[s[left]] > t_hash[s[left]]) {
                printf("==s[left]:%c,left:%d,s_hash[%c]:%d\n",s[left],left,s[left],s_hash[s[left]]);
                s_hash[s[left]]--;
                left++;
            }

            // 更新最短子字符串的长度和起始位置
            window_len = right - left + 1;
            printf("right:%d,left:%d,window:%d,min_len:%d\n",right,left,window_len,min_len);
            if (window_len < min_len) {
                min_len = window_len;
                start = left;
                printf("min_len:%d,start:%d\n",min_len,start);
            }
        }

        right++;
    }

    // 根据最短子字符串的起始位置和长度构建结果字符串
    if (min_len == s_len + 1) {
        return "";
    } else {
        char* result = (char*)malloc((min_len + 1) * sizeof(char));
        strncpy(result, s + start, min_len);
        result[min_len] = '\0';
        return result;
    }
}

int main() {
    //char s[] = "ADOBECODEBANC";
    //char s[] = "AADOBEFCODEBANC";
    char s[] = "ADOBEFCODEBANC";
    char t[] = "ABC";
    
    char* result = minWindow(s, t);
    printf("Output: %s\n", result);
    
    return 0;
}
/*
s_len:14,t_len:3
s[right]:A,right:0,s_hash[A]:1,count:1
s[right]:D,right:1,s_hash[D]:1,count:1
s[right]:O,right:2,s_hash[O]:1,count:1
s[right]:B,right:3,s_hash[B]:1,count:2
s[right]:E,right:4,s_hash[E]:1,count:2
s[right]:F,right:5,s_hash[F]:1,count:2
s[right]:C,right:6,s_hash[C]:1,count:3
right:6,left:0,window:7,min_len:15
min_len:7,start:0
s[right]:O,right:7,s_hash[O]:2,count:3
right:7,left:0,window:8,min_len:7
s[right]:D,right:8,s_hash[D]:2,count:3
right:8,left:0,window:9,min_len:7
s[right]:E,right:9,s_hash[E]:2,count:3
right:9,left:0,window:10,min_len:7
s[right]:B,right:10,s_hash[B]:2,count:3
right:10,left:0,window:11,min_len:7
s[right]:A,right:11,s_hash[A]:2,count:3
==s[left]:A,left:0,s_hash[A]:2
==s[left]:D,left:1,s_hash[D]:2
==s[left]:O,left:2,s_hash[O]:2
==s[left]:B,left:3,s_hash[B]:2
==s[left]:E,left:4,s_hash[E]:2
==s[left]:F,left:5,s_hash[F]:1
right:11,left:6,window:6,min_len:7
min_len:6,start:6
s[right]:N,right:12,s_hash[N]:1,count:3
right:12,left:6,window:7,min_len:6
s[right]:C,right:13,s_hash[C]:2,count:3
==s[left]:C,left:6,s_hash[C]:2
==s[left]:O,left:7,s_hash[O]:1
==s[left]:D,left:8,s_hash[D]:1
==s[left]:E,left:9,s_hash[E]:1
right:13,left:10,window:4,min_len:6
min_len:4,start:10
Output: BANC
]
 */
