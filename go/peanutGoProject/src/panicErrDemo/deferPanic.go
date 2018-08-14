package main

import (
	"fmt"
	"reflect"
)

func main() {
	//funcA()
	funcB()
}

func funcA() {
	defer func() {
		fmt.Println("recover defer func")
		if err := recover(); err != nil {
			fmt.Println(err)
		} else {
			fmt.Println("fatal")
		}
	}()

	defer func() {
		fmt.Println("panic defer func")
		panic("defer panic")
	}()

	//panic仅有最后一个可以被revover捕获
	//触发panic("panic")后顺序执行defer，但是defer中还有一个panic，所以覆盖了之前的panic("panic")
	panic("panic") //看来这个panic会被defer中的panic覆盖
}

func funcB() {
	defer func() {
		if err := recover(); err != nil {
			fmt.Println("recover one panic")
			f := err.(func() string) //type assert
			fmt.Println(err, f(), reflect.TypeOf(err).Kind().String()) //!!!get the type
		} else {
			fmt.Println("fatal")
		}
	}()

	defer func() {
		panic(func() string {
			return "defer panic"
		})
	}()
	panic("panic")
}
