package main

import "fmt"

func main() {
	//var i interface{} := "hello"
	var i interface{} = "hello"


	a := i.(string)
	fmt.Println(a)

	b,ok := i.(string)
	fmt.Println(b,ok)

	c,ok := i.(float32)
	fmt.Println(c,ok)

	//panic
	//d  := i.(float32)
	//fmt.Println(d)

	fmt.Println("aaa:",10,"bbb:",true)
	
}
