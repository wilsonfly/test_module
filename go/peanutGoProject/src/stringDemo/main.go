package main

import (
	"fmt"
	"strings"
)

func main() {

	s, v := func1()
	fmt.Println(s, v)

	func2()

}

func func1() (string, int) {
	return "", 1

	//nil 可以用作 interface、function、pointer、map、slice 和 channel 的“空值”。
	// 但是如果不特别指定的话，Go 语言不能识别类型，所以会报错。报:cannot use nil as type string in return argument.
	//return nil, 0
}

func func2() {
	s := "  hello  world ! "
	fmt.Println(s, len(s))

	sp := strings.Split(s, " ")
	fmt.Println(sp, len(sp)) //huasheng TODO: len???

	trim := strings.TrimSpace(s)
	fmt.Println(trim, len(trim))

}
