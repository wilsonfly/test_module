package main

import (
	"unicode"
	"fmt"
	"unicode/utf8"
)

func main() {
	r := toLower("HELLO World!")
	fmt.Println(r)

	r = toLowerFirtChar("HELLO World!")
	fmt.Println(r)

	r = toLowerFirtChar("")
	fmt.Println(r)

	r = toLowerFirtChar("H")
	fmt.Println(r)

	calcTheLen()

	rangeString()
}

func toLower(s string) string {
	if len(s) < 1 {
		return ""
	}

	r := []rune(s)
	low := []rune{}
	for i := 0; i < len(r); i++ {
		low = append(low, unicode.ToLower(r[i]))
	}

	fmt.Println(low)

	result := string(low)
	return result
}

func toLowerFirtChar(s string) string {
	if len(s) < 1 {
		return ""
	}

	r := []rune(s)
	r[0] = unicode.ToLower(r[0])
	result := string(r)
	return result
}

func calcTheLen(){
	str := "hello 世界"
	fmt.Println(str, len(str))//golang中的string底层是由一个byte数组实现的，utf-8中一个中文占三个字节，golang默认的编码是utf-8编码
	fmt.Println(utf8.RuneCountInString(str))

	r := []rune(str)
	fmt.Println(len(r))
}

func rangeString(){
	str := "hello 世界"
	fmt.Println("in rangeString",str)

	//bad idea
	for i:=0;i<len(str);i++{
		//fmt.Printf("%v",str[i])
		//fmt.Println(string(str[i]))
		fmt.Printf("%c",str[i])
	}
	fmt.Println()

	//no1 range会隐式的unicode解码
	for _,c := range str{
		//fmt.Printf("%v",c)
		//fmt.Println(string(c))
		fmt.Printf("%c",c)
	}
	fmt.Println()

	//no2
	r := []rune(str)
	for _,c := range r{
		//fmt.Println(string(c))
		fmt.Printf("%c",c)
	}
	fmt.Println()
}