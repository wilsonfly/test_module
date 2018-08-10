package main

import "fmt"

func main() {

	s, v := func1()
	fmt.Println(s, v)
}

func func1() (string, int) {
	return "", 1

	//nil 可以用作 interface、function、pointer、map、slice 和 channel 的“空值”。
	// 但是如果不特别指定的话，Go 语言不能识别类型，所以会报错。报:cannot use nil as type string in return argument.
	//return nil, 0
}
