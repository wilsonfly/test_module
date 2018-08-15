package main

import "fmt"
import "interfaceDemo/user"

type ability interface {
	Do()
	Dump()
}

type Admin struct {
	name  string
	age   int
	level string
}

func (a *Admin) Do() {
	fmt.Printf("Admin %v @ %v doing some work\n", a.name, a.age)
}

func (a *Admin) Dump() {
	fmt.Printf("Admin.name:%v,Admin.age:%v, Admin.level:%v\n", a.name, a.age, a.level)
}

func main() {
	y := user.New()
	z := &Admin{
		name:  "HuaSheng",
		age:   19,
		level: "super",
	}
	x := []ability{
		y,
		z,
	}
	fmt.Println(x)

	ab := []ability{
		user.New(),
		&Admin{name: "huasheng", age: 19, level: "super"},
	}

	for _,a := range ab{
		a.Do()
		a.Dump()
	}
}
