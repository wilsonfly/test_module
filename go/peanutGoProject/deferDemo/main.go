package main

import "fmt"

func main() {
	a := 1
	b := 2

	//defer sum("1", a,sum("10", a, b));a=0
	//defer sum("2", a,sum("20", a, b));b=1

	defer sum("1", a,sum("10", a, b))
	defer sum("2", a,sum("20", a, b))
}

func sum(index string, a int, b int) int {
	ret := a+b
	fmt.Println(index,a,b,ret)
	return ret
}