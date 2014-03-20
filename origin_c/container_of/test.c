
#include <stdio.h>

#include "container_of.h"

typedef struct __struct
{
	int a;
	int b;
	char c;
	char d;
	char* e;
	char* f;
}test_t;

int main()
{
	test_t test;
	int size;
	printf("offset a:%d\n",offset(test_t,a));
	printf("offset b:%d\n",offset(test_t,b));
	printf("offset c:%d\n",offset(test_t,c));
	printf("offset d:%d\n",offset(test_t,d));
	printf("offset e:%d\n",offset(test_t,e));
	printf("offset e:%d\n",offset(test_t,e));

	printf("addr_a:0x%p, addr_test:0x%p\n",&test.a,container_of_s(&test.a, test_t, a));
	printf("addr_b:0x%p, addr_test:0x%p\n",&test.b,container_of_s(&test.b, test_t, b));
	printf("addr_c:0x%p, addr_test:0x%p\n",&test.c,container_of_s(&test.c, test_t, c));
	printf("addr_a:0x%p, addr_test:0x%p\n",&test.a,container_of(&test.a, test_t, a));
	printf("addr_b:0x%p, addr_test:0x%p\n",&test.b,container_of(&test.b, test_t, b));
	printf("addr_c:0x%p, addr_test:0x%p\n",&test.c,container_of(&test.c, test_t, c));
}
