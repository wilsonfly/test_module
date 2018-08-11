package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	filename := "stringDemo/data.txt"
	//f,err := os.Open(filename)
	contents, err := ioutil.ReadFile(filename)
	if err!= nil{
		fmt.Println("open file error:",err)
		return
	}

	s := string(contents)
	num := CountWords(s)
	fmt.Println(num)
}

func CountWords(s string) int{
	f := strings.Fields(s) //处理空格
	fmt.Println(f)

	num := len(f)
	return num
}
