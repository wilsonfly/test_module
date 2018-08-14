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

	//四个全是方法的调用
	ad.user.dump()
	ad.dump() //嵌入类型的方法的提升

	ad.user.varDump()
	ad.varDump()
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

func (u user)varDump(){
	fmt.Println(u.name,u.email)
}