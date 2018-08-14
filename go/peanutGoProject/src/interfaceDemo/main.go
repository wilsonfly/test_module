package main

import (
	"fmt"
)

type People interface {
	Speak(string) string
}
type Student struct{}

func (stu Student) Speak(think string) (talk string) {
//func (stu *Student) Speak(think string) (talk string) {
	if think == "beautiful" {
		talk = "Such a pretty girl"
	} else {
		talk = "hi"
	}
	return
}

//func (stu *Student)String() string{
func (stu Student)String() string{
	return "hello"
}

func getOneValue() int{
	return 1
}

func getOneInterface() interface{} {
	return 1
}

func main() {
	//====begin====
	fmt.Println("no1 ========")
	var p1 People = Student{}
	var p2 People = &Student{}
	think := "beautiful"
	fmt.Println(p1.Speak(think))
	fmt.Println(p2.Speak(think))
	//====end====


	//====begin====
	fmt.Println("no2 ========")
	var any interface{}  // initialized elsewhere
	any = p1
	s := any.(fmt.Stringer)  // dynamic conversion
	fmt.Println(s.String())
	fmt.Println(s)
	//====end====


	//====begin====
	fmt.Println("no3 ========")
	//v1 := getOneValue()
	v1 := getOneInterface()
	//只有interface类型的变量才能用type switch
	switch v1.(type) {
	case int:
		fmt.Println("type int")
	default:
		fmt.Println("type unknown")
	}
	//====end====


	//====begin====
	fmt.Println("no0 ========")
	//====end====

	//====begin====
	fmt.Println("no0 ========")
	//====end====

	//====begin====
	fmt.Println("no0 ========")
	//====end====
}
