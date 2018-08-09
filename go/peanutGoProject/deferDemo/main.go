package main

import "fmt"

func main() {
	a := 1
	b := 2
	//====begin====
	fmt.Println("no1 ========")
	//defer sum("1", a,sum("10", a, b));a=0
	//defer sum("2", a,sum("20", a, b));b=1

	defer sum("1", a, sum("10", a, b))
	defer sum("2", a, sum("20", a, b))
	//====end====


	//====begin====
	fmt.Println("no2 ========")
	fmt.Println("DeferFunc1:",DeferFunc1(1))
	fmt.Println("DeferFunc2:",DeferFunc2(1))
	fmt.Println("DeferFunc3:",DeferFunc3(1))
	//====end====



	//====begin====
	fmt.Println("no ========")
	//====end====
}

func sum(index string, a int, b int) int {
	ret := a + b
	fmt.Println(index, a, b, ret)
	return ret
}

func DeferFunc1(i int) (t int) {
	t = i
	defer func() {
		t += 3
	}()
	return t
}

func DeferFunc2(i int) int {
	t := i
	defer func() {
		t += 3
	}()
	return t
}

func DeferFunc3(i int) (t int) {
	defer func() {
		t += i
	}()
	return 2
}
