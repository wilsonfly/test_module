package main

import "fmt"

func getValue(v interface{}) {
	//必须是interface{}类型才能类型断言
	a := v.(string)
	fmt.Println(a, )

	b, ok := v.(string)
	fmt.Println(b, ok)

	c, ok := v.(float32)
	fmt.Println(c, ok)

}

func main() {
	//var i interface{} := "hello"
	var i interface{} = "hello"

	//必须是interface{}类型才能类型断言
	a := i.(string)
	fmt.Println(a, )

	b, ok := i.(string)
	fmt.Println(b, ok)

	c, ok := i.(float32)
	fmt.Println(c, ok)

	//panic
	//d  := i.(float32)
	//fmt.Println(d)

	fmt.Println("aaa:", 10, "bbb:", true)

	getValue("world")
}
