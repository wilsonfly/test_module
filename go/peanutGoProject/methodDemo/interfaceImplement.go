package main

import "fmt"

func main() {
	hua:= user{name:"hua",email:"hua@qq.com"}
	sheng:=&user{name:"sheng",email:"sheng@qq.com"}

	hua.varDump()
	sheng.varDump()

	hua.pointerDump()
	sheng.pointerDump()

	//callVarDump(hua)
	callVarDump(sheng)

	//callPointerDump(hua)
	callPointerDump(sheng)
}

type user struct {
	name  string
	email string
}

type Idump interface {
	varDump()
	pointerDump()
}

func (u user)varDump(){
	fmt.Println(u.name,u.email)
}

func (u *user)pointerDump(){
	fmt.Println(u.name,u.email)
}

func callVarDump(z Idump){
	z.varDump()
}

func callPointerDump(z Idump){
	z.pointerDump()
}
