package main

import (
	"fmt"
	"gx/ipfs/QmVmDhyTTUcQXFD1rRQ64fGLMSAoaQvNH3hwuaCFAPq2hy/errors"
)

func main() {
	//fmt.Println(test())
	//test2()
	//test3()
	test4()
}

func test() string {
	defer fmt.Println("defer info 1")
	defer fmt.Println("defer info 2")
	panic("panic info")
	fmt.Println("println info")
	return "return info"
}

func test2() {
	defer func() {
		if r := recover(); r != nil {
			fmt.Println("recover something:", r)
		}
	}()
	panic("panic in test2")
	fmt.Println("after panic in test2")
}

func test3() {
	//err := funcA()
	err := funcA2()
	if err == nil {
		fmt.Printf("err is nil\n") //得到err为nil
	} else {
		fmt.Printf("err is %v\n", err)
	}
}

func funcA2() (err error) {
	defer func() {
		fmt.Println("in defer func of funcA")
		if p := recover(); p != nil {
			fmt.Printf("panic recover! p: %v\n", p)
			s, ok := p.(string)
			if ok {
				err = errors.New("recovered panic:"+s)
			} else {
				err = errors.New("ops")
			}
			//debug.PrintStack()
		}
	}()
	return funcB() //return 得不到执行
}

func funcA() error {
	defer func() {
		fmt.Println("in defer func of funcA")
		if p := recover(); p != nil {
			fmt.Printf("panic recover! p: %v\n", p)
			//debug.PrintStack()
		}
	}()
	return funcB() //panic 打断接下来的代码，但是不打断defer代码
}

func funcB() error {
	fmt.Println("in funcB")
	panic("foo")
	return errors.New("success") //panic 打断接下来的代码，return 得不到执行
}

func test4() {
	num := 1
	err := funcC()
	defer func() {
		fmt.Println("clear something for funcC ", err, num)
	}()

	num = 2
	err = funcD()
	defer func() {
		fmt.Println("clear something for funcD ", err, num)
	}()

	num = 3
	err = funcE()
	if err != nil {
		fmt.Println("clear something for funcE ", err, num)
	}
}

func funcC() error {
	return errors.New("err in funcC")
}
func funcD() error {
	return errors.New("err in funcD")
}
func funcE() error {
	return errors.New("err in funcE")
}
