#include <stdio.h>
#include <string.h>

/*
句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。

例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
给你一个句子s和一个整数k，你将s截断，使截断后的句子仅含前k个单词。返回 截断s后得到的句子。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/truncate-sentence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
char *truncateSentence(char *s, int k) {
    int num = 0;
    char *p = NULL;
    int len = strlen(s);
    char *buf = (char *)malloc(sizeof(char) * (len + 1));

    memset(buf, 0, len + 1);
    strcpy(buf, s);
    p = buf;
    while ((p = strstr(p, " ")) != NULL && ++num < k) {
        p = p+1;
        //printf("p:%s,num:%d\n",p,num);
    }
    if (p != NULL) {
        *p = '\0';
    }
    //printf("%s\n", buf);
    return buf;
}

int main()
{
    printf("%s\n", truncateSentence("Hello how are you Contestant", 4));
    printf("%s\n", truncateSentence("Hello how are you Contestant", 2));
}
