package main

import (
	"fmt"
	"./tools"
)

func main() {
	u := tools.NewUser()
	fmt.Println(u)
	fmt.Println(u.Name, u.Email)

	//a := tools.Admin{Name:"huasheng", email:"huasheng@qq.com"}
	//b := tools.Admin{"huasheng","huasheng@qq.com"} //即使不明写email也编译不过

	r := tools.Root{
		Level:"superUser",
	}
	r.Name = "huasheng"
	r.Email = "huasheng@qq.com"

	fmt.Println(r)
}
