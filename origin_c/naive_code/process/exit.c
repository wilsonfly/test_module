#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
int main()
{
	printf("Using exit....\n");
	printf("this is the content in buffer");
	exit(0);
	//_exit(0);
	return 0;
}
