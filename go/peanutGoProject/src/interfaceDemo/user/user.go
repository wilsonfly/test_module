package user

import "fmt"

type User struct {
	Name string
	Age int
}

func (u *User)Do(){
	fmt.Printf("user %v @ %v years old, doing some hard work\n",u.Name,u.Age)
}

func (u *User)Dump(){
	fmt.Printf("user.name:%v, user.age:%v\n",u.Name,u.Age)
}

func New() *User{
	a := User{
		Name:"huasheng",
		Age:18,
	}

	return &a
}