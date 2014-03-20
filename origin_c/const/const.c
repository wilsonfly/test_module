
#include "stdio.h"

int main()
{
	const int a = 9;
	int *p = (int*)&a;
	*p = 90;
	printf("a:%d,*p:%d\n",a,*p);
}
