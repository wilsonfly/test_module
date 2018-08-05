package main

import "fmt"

func getType(i interface{}){
	switch v := i.(type) {
	case int:
		fmt.Printf("get int:%v \n", i)
	case string:
		fmt.Printf("get string:%s \n", i)
		fmt.Printf("get string:%v \n", i)
		fmt.Printf("get string:%q \n", i)
	default:
		fmt.Printf("don't know the type:%T \n",v)
	}
}

func main() {
	getType(20)
	getType("hello world!")
	getType(true)
}
