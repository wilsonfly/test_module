
#include <stdio.h>
int main()
{
   int a[2][3]={1,2,3,4,5,6};
   int *b[2];
   b[0]=a[0];
   printf("b[0]=%d\n",*b[0]);
   printf("b[0]=%d\n",**b);
   b[1]=a[1];

   printf("*b[1]=%d\n",*b[1]);
   printf("**(b+1)=%d\n",**(b+1));
 
   printf("*(b[0]+1)=%d\n",*(b[0]+1));
   printf("*(*b+1)=%d\n",*(*b+1));
   
   printf("*(b[1]+1)=%d\n",*(b[1]+1));
   printf("*(*(b+1))+1=%d\n",*(*(b+1)+1));

}
