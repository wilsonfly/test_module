package main

import (
	"fmt"
	"reflect"
)

//type user struct {
//	id   int
//	name string
//	age  int
//}
type user struct {
	Id   int
	Name string
	Age  int
}

func main() {
	//testBasic()
	//testConvert()

	u := user{Id: 110, Name: "huasheng", Age: 18}
	//getFieldMethod(u)

	x := 4
	switch {
	case x == 0:
		testBasic()
	case x == 1:
		testConvert()
	case x == 2:
		getFieldMethod(u)
	case x == 3:
		setValue()
	case x == 4:
		callMethod(u)

	}
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

func testConvert() {
	num := 1.234
	//num := 1234 //panic: interface conversion: interface {} is *int, not *float64

	//thePointer := reflect.TypeOf(&num)
	//theValue := reflect.TypeOf(num)

	thePointer := reflect.ValueOf(&num)
	theValue := reflect.ValueOf(num)

	newPointer := thePointer.Interface().(*float64)
	newValue := theValue.Interface().(float64)

	fmt.Println(newPointer, newValue)
	fmt.Println(thePointer, theValue)
}

//func (u User)oneMysteriousFunc() {
//user的方法也需要是exported，否则theType.NumMethod()为0
func (u user) OneMysteriousFunc() {
	fmt.Println("say hi from oneMysteriousFunc")
}

func (u user) OneFuncWithArgs(a int, b string) {
	fmt.Println("in OneFuncWithArgs,args:", a, b)
}

func getFieldMethod(x interface{}) {
	theType := reflect.TypeOf(x)
	theValue := reflect.ValueOf(x)
	fmt.Println("the type:", theType)
	fmt.Println("the value:", theValue)

	for i := 0; i < theType.NumField(); i++ {
		field := theType.Field(i)
		//value := theType.Field(i).Interface()

		//user的成员得是exported，否则panic: reflect.Value.Interface: cannot return value obtained from unexported field or method
		value := theValue.Field(i).Interface()
		fmt.Printf("%v:%v:%v \n", field.Name, field.Type, value)
	}

	//user的方法也需要是exported，否则theType.NumMethod()为0
	fmt.Println("nummethod:", theType.NumMethod())
	for i := 0; i < theType.NumMethod(); i++ {
		method := theType.Method(i)
		fmt.Printf("the name:%v, the type:%v \n", method.Name, method.Type)
	}
}

func setValue() {
	num := 1.234

	theType := reflect.TypeOf(num)
	theValue := reflect.ValueOf(num)
	//newValue := theValue.Elem() //panic: reflect: call of reflect.Value.Elem on float64 Value
	fmt.Println(theType, theValue, theValue.CanSet())

	//通过reflect.ValueOf获取num中的reflect.Value，参数必须是指针才能修改其值
	theType = reflect.TypeOf(&num)
	theValue = reflect.ValueOf(&num)
	//reflect.Value.Elem() 表示获取原始值对应的反射对象，只有原始对象才能修改，当前反射对象是不能修改的
	newValue := theValue.Elem()
	fmt.Println(theType, theValue, theValue.CanSet(), newValue.Type(), newValue.CanSet())

	newValue.SetFloat(9.99)
	fmt.Println("after set:", newValue)

}

func callMethod(x interface{}) {
	theValue := reflect.ValueOf(x)

	method := theValue.MethodByName("OneMysteriousFunc")

	//src/reflectDemo/main.go:133:13: not enough arguments in call to method.Call
	//have ()
	//want ([]reflect.Value)
	//method.Call()

	args := make([]reflect.Value, 0)
	method.Call(args)

	method2 := theValue.MethodByName("OneFuncWithArgs")
	args2 := []reflect.Value{reflect.ValueOf(18), reflect.ValueOf("string arg")}
	method2.Call(args2)
}
