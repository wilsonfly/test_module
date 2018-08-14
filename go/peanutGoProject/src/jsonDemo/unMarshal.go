package main

import (
	"encoding/json"
	"fmt"
)

//huasheng mark:结构内的成员必须是外部可见的！
type Contact struct {
	Name  string `json:"name"`
	Title string `json:"title"`
	//huasheng mark:这里居然可以定义一个同名的结构体
	Contact struct {
		Var1 string `json:"var_1"`
		Var2 string `json:"var_2"`
	} `json:"inner"`
}

type Contact2 struct {
	Name  string `json:"name"`
	Title string `json:"title"`
	InnerStruct struct {
		Var1 string `json:"var_1"`
		Var2 string `json:"var_2"`
	} `json:"inner"`
}

//huasheng mark:逗号留意，缺了逗号会：unmarshal error
var JSON = `{
	"name":"huasheng",
	"title":"TestContact",
	"inner":{
		"var_1":"aaa",
		"var_2":"bbb"
	}
}`

func main() {
	var c Contact
	err := json.Unmarshal([]byte(JSON),&c)
	if err!=nil{
		fmt.Println("unmarshal error")
		return
	}
	fmt.Println(c)


	var c2 Contact2
	err2 := json.Unmarshal([]byte(JSON),&c2)
	if err2!=nil{
		fmt.Println("unmarshal error")
		return
	}
	fmt.Println(c2)


}
