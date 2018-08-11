package main

import "fmt"

type user struct {
	name string
	email string
}

func (u user)dump(){
	fmt.Println(u.name,u.email)
}

func (u *user)changeEmail(s string){
	u.email = s
}

func main() {
	hua := user{name:"hua",email:"hua@qq.com"}
	sheng := &user{name:"sheng",email:"sheng@qq.com"}

	hua.dump() //user类型的值 调用 user类型接受者的方法
	sheng.dump() //user指针类型的值 调用 user类型接受者的方法


	hua.changeEmail("hua@new.com") //user类型的值 调用 user指针类型接受者的方法
	hua.dump()

	sheng.changeEmail("sheng@new.com") //user指针类型的值 调用 user指针类型接受者的方法
	sheng.dump()
}
