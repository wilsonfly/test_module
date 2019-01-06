package main

import (
	"regexp"
	"fmt"
)

//const text string = "my email is wilsonfly.shs@gmail.com"
//const text2 string = "my email is shs@gmail.com"

func main() {
	texts := []string{"my email is wilsonfly.shs@gmail.com",
		"my email is shs@gmail.com.cn",
		"my email is shs@gmail.com",
		"@gmail.com",
		"my email is wilsonfly.shs@gmail.com, keep in touch.",
		`email1 111@gmail.com
		 email2  222@gmail.com.cn
		 email3 333@qq.com `,
	}

	fmt.Println("====001")
	//compile := regexp.MustCompile("shs@gmail.com")
	mustcompile := regexp.MustCompile("wilsonfly.shs@gmail.com")
	for i := 0; i < len(texts); i++ {
		s := mustcompile.FindString(texts[i])
		fmt.Println(i, s)
	}

	fmt.Println("====002")
	mustCompile2 := regexp.MustCompile(".+@.+\\..+")
	for i := 0; i < len(texts); i++ {
		s := mustCompile2.FindString(texts[i])
		fmt.Println(i, s)
	}

	fmt.Println("====003")
	mustCompile3 := regexp.MustCompile(`.+@.+..+`)
	for i := 0; i < len(texts); i++ {
		s := mustCompile3.FindString(texts[i])
		fmt.Println(i, s)
	}

	fmt.Println("====004")
	mustCompile4 := regexp.MustCompile(`.*@.+..+`)
	for i := 0; i < len(texts); i++ {
		s := mustCompile4.FindString(texts[i])
		fmt.Println(i, s)
	}

	fmt.Println("====005")
	mustCompile5 := regexp.MustCompile(`[a-zA-Z0-9.]*@.+..+`)
	for i := 0; i < len(texts); i++ {
		s := mustCompile5.FindString(texts[i])
		fmt.Println(i, s)
	}

	fmt.Println("====006")
	mustCompile6 := regexp.MustCompile(`[a-zA-Z0-9.]+@[a-zA-Z0-9]+.[a-zA-Z0-9.]+`)
	for i := 0; i < len(texts); i++ {
		s := mustCompile6.FindString(texts[i])
		fmt.Println(i, s)
	}

	fmt.Println("====007")
	//mustCompile7 := regexp.MustCompile(`[a-zA-Z0-9.]*@[a-zA-Z0-9]+.[a-zA-Z0-9.]+`)
	mustCompile7 := regexp.MustCompile(`[a-zA-Z0-9.]+@[a-zA-Z0-9]+\.[a-zA-Z0-9.]+`) //也ok
	for i := 0; i < len(texts); i++ {
		s := mustCompile7.FindAllString(texts[i],-1)
		fmt.Println(i, s)
	}

	fmt.Println("====008")
	mustCompile8 := regexp.MustCompile(`([a-zA-Z0-9.]+)@([a-zA-Z0-9]+)(.[a-zA-Z0-9.]+)`) //也ok
	for i := 0; i < len(texts); i++ {
		s := mustCompile8.FindAllStringSubmatch(texts[i],-1)
		fmt.Println(i, s)
	}


}
