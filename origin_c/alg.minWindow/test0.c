#include <stdio.h>
#include <string.h>

char* minWindow(char* s, char* t) {
    int s_len = strlen(s);
    int t_len = strlen(t);

    if (s_len < t_len) return "";

    int t_count[256] = {0};
    int i;

    for (i = 0; i < t_len; i++) {
        t_count[t[i]]++;
    }

    int left = 0, right = 0, count = t_len;
    int min_len = s_len + 1, min_start = 0;

    while (right < s_len) {
        if (t_count[s[right]] > 0) {
            count--;
        }

        t_count[s[right]]--;
        right++;

        while (count == 0) {
            if (right - left < min_len) {
                min_len = right - left;
                min_start = left;
            }

            t_count[s[left]]++;

            if (t_count[s[left]] > 0) {
                count++;
            }

            left++;
        }
    }

    if (min_len == s_len + 1) {
        return "";
    } else {
        char* result = (char*)malloc((min_len + 1) * sizeof(char));
        strncpy(result, s + min_start, min_len);
        result[min_len] = '\0';
        return result;
    }
}

int main() {
    char s[] = "ADOBECODEBANC";
    char t[] = "ABC";
    
    char* result = minWindow(s, t);
    printf("Output: %s\n", result);
    
    return 0;
}
