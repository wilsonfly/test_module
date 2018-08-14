package main

import (
	"encoding/json"
	"fmt"
)

func main() {
	m := make(map[string]interface{})
	m["name"] = "huasheng"
	m["title"] = "TestContact"

	//方法一：
	m["inner"] = make(map[string]interface{})
	//m["inner"].["var_1"] = "aaa"
	//m["inner"].["var_2"] = "bbb"
	m["inner"].(map[string]interface{})["var_1"] = "aaa"
	m["inner"].(map[string]interface{})["var_2"] = "bbb"

	//方法二：字面量
	m["inner"] = map[string]interface{}{
		"var_1": "ccc",
		"var_2": "ddd",
	}

	b, err := json.MarshalIndent(m, "", "  ")
	if err != nil {
		fmt.Println("marshal failed")
		return
	}
	//fmt.Println(b.(string))//invalid type assertion: b.(string) (non-interface type []byte on left)
	fmt.Println(string(b))
}
