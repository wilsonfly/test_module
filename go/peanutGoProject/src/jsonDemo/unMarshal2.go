package main

import (
	"encoding/json"
	"fmt"
)


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
	var m map[string]interface{}
	err := json.Unmarshal([]byte(JSON),&m)
	if err!=nil{
		fmt.Println("unmarshal error")
		return
	}
	fmt.Println(m["name"])
	fmt.Println(m["title"])

	//取出m["inner"] 然后进行类型转换：var.(type) ，此处变量var为m["inner"],类型为map[string]interface{}
	//得到inner结构也是个map类型，再取里边的值xx["var_1"]
	fmt.Println(m["inner"].(map[string]interface{})["var_1"])
	fmt.Println(m["inner"].(map[string]interface{})["var_2"])



}
