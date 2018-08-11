package main

import "fmt"

func main() {
	hua:= user{name:"hua",email:"hua@qq.com"}
	sheng:=&user{name:"sheng",email:"sheng@qq.com"}

	hua.dump()    //exec ok, call a method
	sheng.dump()  //exec ok, call a method

	callDump(hua)
	callDump(sheng)
}

type user struct {
	name  string
	email string
}

type Idump interface {
	dump()
}


//只能声明一个dump，要么用user要么用*user作为接收者
//method redeclared: user.dump
//	method(user) func()
//	method(*user) func()
//
func (u user)dump(){
	fmt.Println(u.name,u.email)
}

//func (u *user)dump(){
//	fmt.Println(u.name,u.email)
//}

func callDump(z Idump){
	z.dump()
}


