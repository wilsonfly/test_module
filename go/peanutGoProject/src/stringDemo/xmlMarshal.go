package main

import (
	"encoding/xml"
	"fmt"
)

type person struct {
	Name string
	Age  int
}

func main() {
	p := person{Name: "peanut", Age: 18}
	if b, err := xml.MarshalIndent(p, "", " "); err!=nil{
		fmt.Println(err)
		return
	}else {
		//fmt.Println(b)
		fmt.Println(string(b))
	}

	//fmt.Println(string(b)) //b的作用域到不了这里

}
