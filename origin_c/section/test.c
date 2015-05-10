#include <stdio.h>
#include <time.h>

int global1_B;
int global2_D=1;

static int static_global1_b;
static int static_global2_d=1;

extern void extern_foo();

void foo()
{
	static int static_internal1_b;
	static int static_internal2_d=1;
	time(0);
}

static void bar()
{
	printf("hello\n");
}

int main()
{
	int local1_b;
	int local2_d=0;

	foo();
	bar();
	extern_foo();

	return 0;
}
