package main

import (
	"fmt"
	"reflect"
)

func main() {
	//testBasic()
	testConvert()
}

func testBasic() {
	var num float64 = 1.234
	s := "hello"

	var i interface{}
	i = true

	fmt.Println("type:", reflect.TypeOf(num))
	fmt.Println("value:", reflect.ValueOf(num))

	fmt.Println("type:", reflect.TypeOf(s))
	fmt.Println("value:", reflect.ValueOf(s))

	fmt.Println("type:", reflect.TypeOf(i))
	fmt.Println("value:", reflect.ValueOf(i))
}

func testConvert(){
	num := 1.234
	//num := 1234 //panic: interface conversion: interface {} is *int, not *float64

	//thePointer := reflect.TypeOf(&num)
	//theValue := reflect.TypeOf(num)

	thePointer := reflect.ValueOf(&num)
	theValue := reflect.ValueOf(num)

	newPointer := thePointer.Interface().(*float64)
	newValue := theValue.Interface().(float64)

	fmt.Println(newPointer,newValue)
	fmt.Println(thePointer,theValue)
}