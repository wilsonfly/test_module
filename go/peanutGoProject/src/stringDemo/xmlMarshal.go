package main

import (
	"encoding/xml"
	"fmt"
)

type person struct {
	Name string `xml:"name"`//`xml:"name,attr"`
	Age  int `xml:"年龄,attr"`
}

func main() {
	p := person{Name: "peanut", Age: 18}
	if b, err := xml.MarshalIndent(p, "", " "); err != nil {
		fmt.Println(err)
		return
	} else {
		//fmt.Println(b)
		fmt.Println(string(b))

		p2 := new(person)
		xml.Unmarshal(b, p2)
		fmt.Println(p2)
	}

	//fmt.Println(string(b)) //b的作用域到不了这里

}
