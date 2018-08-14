package main

import "fmt"

func main() {
	f1 := add(1, 2)
	fmt.Println(f1())
	fmt.Println(f1())
	fmt.Println(f1())


	f2 := add2(1,2)
	fmt.Println(f2(3,4))
	fmt.Println(f2(5,6))

	f3 := add2(1,2)
	fmt.Println(f3(3,4))
	fmt.Println(f3(5,6))
}

func add(x1, x2 int) func() (int, int) {
	i := 0
	return func() (int, int) {
		i++
		return i, x1 + x2
	}
}

func add2(x1, x2 int) func(x3, x4 int) (int, int, int) {
	i := 0
	return func(x3, x4 int) (int, int, int) {
		i++
		return i, x1 + x2, x3 + x4
	}
}
