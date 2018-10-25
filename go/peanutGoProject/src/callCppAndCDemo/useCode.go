package main

/*
#include <stdio.h>

int add(int a, int b)
{
	printf("in func add\n");
	return a+b;
}
 */
import "C"
import "fmt"

func main() {
	fmt.Println(C.add(2,3))
}
