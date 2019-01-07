package main

import (
	"regexp"
	"fmt"
	"io/ioutil"
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
		s := mustCompile7.FindAllString(texts[i], -1)
		fmt.Println(i, s)
	}

	fmt.Println("====008")
	mustCompile8 := regexp.MustCompile(`([a-zA-Z0-9.]+)@([a-zA-Z0-9]+)(.[a-zA-Z0-9.]+)`)
	for i := 0; i < len(texts); i++ {
		s := mustCompile8.FindAllStringSubmatch(texts[i], -1)
		//s := mustCompile8.FindStringSubmatch(texts[i])
		fmt.Println(i, s)
	}

	fmt.Println("====009")
	string9 := " ddd <div data-v-3c42fade>合肥 | abc </div> aaaa"
	compile9 := regexp.MustCompile(`<div data-v-3c42fade>([^\|]+) \| ([a-z]+) </div>`)
	allString9 := compile9.FindAllString(string9, -1)
	submatch9 := compile9.FindAllStringSubmatch(string9, -1)
	fmt.Println(allString9)
	//fmt.Println(submatch9[0])
	for _, s := range submatch9 {
		for j,ss := range s {
			fmt. Println(j, ss)
		}
	}

	fmt.Println("====010")
	//<div class="des f-cl" data-v-3c42fade>合肥 | 24岁 | 大学本科 | 未婚 | 163cm | 5001-8000元</div>
	bytes, err := ioutil.ReadFile("profile_testdata.html")
	if err != nil {
		fmt.Println(err)
	}

	moduleReg := `<div class="des f-cl" data-v-3c42fade>[^|]+ \| ([0-9]+)岁 \| ([^|]+) \| ([^|]+) \| ([0-9]+)cm \| ([^<]+)</div>`
	compile10 := regexp.MustCompile(moduleReg)
	all := compile10.FindAll(bytes, -1)
	for i, s := range all {
		fmt.Println(i, string(s))
	}

	submatch10 := compile10.FindSubmatch(bytes)
	for i, s := range submatch10 {
		fmt.Println(i, string(s))
		//for j, sub := range s {
		//	fmt.Println(j, string(sub))
		//}
	}

}
