#include <stdio.h>
#include <string.h>

char * addStrings(char * num1, char * num2){
    int len1 = strlen(num1);
    int len2 = strlen(num2);
    int i,j,k;
    int len = (len1>len2?len1:len2) + 1;
    int tmp=0,flag=0;
    char *data = (char *)malloc(len+1);

    memset(data,0,(len+1)*sizeof(char));
    for(i=len1-1,j=len2-1,k=0; i>=0 || j>=0; i--,j--)
    {
        if(i<0)
            tmp = num2[j] - '0' + flag;
        else if(j<0)
            tmp = num1[i] - '0' + flag;
        else
            tmp = num1[i] - '0' + num2[j] - '0' + flag;

        flag = tmp/10;
        tmp = tmp%10;

        data[k++] = tmp+'0';
    }
    if(flag == 1)
        data[k++] = '1';

    //printf("%s\n",data);

    //reverse
    for(i=0,j=k-1; i<j; i++,j--)
    {
        tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
    printf("%s\n",data);

    return data;
}
int main()
{
    printf("%s\n",addStrings("235","77"));
}
