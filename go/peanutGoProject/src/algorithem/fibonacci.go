package main

import "fmt"

func main() {

	for i := 0; i < 10; i++ {
		fmt.Println(fibonacci(i))
	}

	f := fib2()
	for i := 0; i < 10; i++ {
		fmt.Println(f())
	}
}

func fibonacci(i int) int {
	if i < 2 {
		return 1
	} else {
		return fibonacci(i-2) + fibonacci(i-1)
	}
}

func fib2() func() int {
	//a, b := 1, 1
	a, b := 0, 1
	return func() int {
		a, b = b, a+b
		return a
	}
}
