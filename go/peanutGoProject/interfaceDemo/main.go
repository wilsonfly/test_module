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

func main() {
	var p1 People = Student{}
	var p2 People = &Student{}
	think := "beautiful"
	fmt.Println(p1.Speak(think))
	fmt.Println(p2.Speak(think))


	var any interface{}  // initialized elsewhere
	any = p1
	s := any.(fmt.Stringer)  // dynamic conversion
	fmt.Println(s.String())
	fmt.Println(s)


}
