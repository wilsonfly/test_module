#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* minWindow(char* s, char* t)
{
    if(s==NULL || t==NULL)
        return "";
    
    int s_len = strlen(s);
    int t_len = strlen(t);
    if(s_len<t_len || t_len==0)
        return "";

    int s_hash[128] = {};
    int t_hash[128] = {};
    int i = 0;
    for(i=0; i<t_len; i++)
    {
        t_hash[t[i]]++;
    }

    int left = right = 0;
    int min_window = s_len + 1;
    int window_len = 0;
    int startIndex = 0;

    while(right < s_len)
    {
        s_hash[s[right]]++;
        if(s_hash_[s[right]] <= t_hash[s[right]])
            count++;

    }
    
}

int main() {
    //char s[] = "ADOBECODEBANC";
    char s[] = "ADOBEFCODEBANC";
    char t[] = "ABC";
    
    char* result = minWindow(s, t);
    printf("Output: %s\n", result);
    
    return 0;
}
