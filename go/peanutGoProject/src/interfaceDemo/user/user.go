package user

import "fmt"

type User struct {
	name string
	age int
}

func (u *User)Do(){
	fmt.Printf("user %v @ %v years old, doing some hard work\n",u.name,u.age)
}

func (u *User)Dump(){
	fmt.Printf("user.name:%v, user.age:%v",u.name,u.age)
}

func New() *User{
	a := User{
		name:"huasheng"
		age:18
	}
}