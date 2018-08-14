package main

import "fmt"
import "interfaceDemo/user"

type ability interface {
	Do()
	Dump()
}

type Admin struct {
	name string
	age int
	level string
}

func (a *Admin)Do(){
	fmt.Printf("Admin %v @ %v doing some work\n",a.name,a.age)
}

func (a *Admin)Dump(){
	fmt.Println("Admin.name:%v,Admin.age:%v, Admin.level:%v",a.name,a.age,a.level)
}

func main() {
	x := []interface{
		User.New(),
		&Admin{name:"HuaSheng",age:19,level:"super"},
	}
}
