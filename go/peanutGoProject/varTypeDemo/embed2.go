package main

import "fmt"

func main() {
	ad := admin{
		user: user{
			name:"huasheng",
			email:"huasheng@qq.com",
		},
		level:"super",
	}

	//方法的调用
	ad.user.dump()
	ad.dump() //嵌入类型的方法的提升

	//callDump(ad) //admin does not implement Idump (dump method has pointer receiver)
	callDump(&ad) //接口类型变量的赋值
}

type Idump interface {
	dump()
}

type user struct {
	name string
	email string
}

type admin struct {
	user
	level string
}

func (u *user)dump(){
	fmt.Println(u.name,u.email)
}

func callDump(z Idump){
	z.dump()
}