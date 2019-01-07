package parser

import (
	"crawler/engine"
	"crawler/model"
	"regexp"
	"fmt"
	"strconv"
)

//<h1 class="nickName" data-v-5b109fc3>靖雯</h1>
const nickNameReg = `<h1 class="nickName" data-v-5b109fc3>([^<]+)</h1>`

var nickNameRegPreCompile = regexp.MustCompile(`<h1 class="nickName" data-v-5b109fc3>([^<]+)</h1>`)

//<div class="id" data-v-5b109fc3>ID：1621215509</div>
const idReg = `<div class="id" data-v-5b109fc3>ID：([^<]+)</div>`

var idRegPreCompile = regexp.MustCompile(`<div class="id" data-v-5b109fc3>ID：([^<]+)</div>`)

//<div class="des f-cl" data-v-3c42fade>合肥 | 24岁 | 大学本科 | 未婚 | 163cm | 5001-8000元</div>
var moduleReg = `<div class="des f-cl" data-v-3c42fade>([^|]+) \| ([0-9]+)岁 \| ([^|]+) \| ([^|]+) \| ([0-9]+)cm \| ([^<]+)</div>`

func ParseProfile(contents []byte) engine.ParseResult {

	personA := model.Personal{}

	//compile := regexp.MustCompile(nickNameReg)
	//submatch := compile.FindSubmatch(contents)
	//if submatch!= nil {
	//	personA.Name = string(submatch[1])
	//}
	//fmt.Println(personA.Name)

	nickName := commonCompileReg(contents, nickNameRegPreCompile)
	personA.Name = nickName
	fmt.Println("nickName:", personA.Name)

	//compileID := regexp.MustCompile(idReg)
	//submatchID := compileID.FindSubmatch(contents)
	//if submatchID != nil {
	//	personA.Id = string(submatchID[1])
	//}
	//fmt.Println(personA.Id)
	id := commonCompileReg(contents, idRegPreCompile)
	personA.Id = id
	fmt.Println("ID:", personA.Id)

	compileModule := regexp.MustCompile(moduleReg)
	submatchModule := compileModule.FindSubmatch(contents)
	if submatchModule != nil {
		personA.Home = string(submatchModule[1])
		personA.Age, _ = strconv.Atoi(string(submatchModule[2]))
		personA.Education = string(submatchModule[3])
		personA.Marriage = string(submatchModule[4])
		personA.Height, _ = strconv.Atoi(string(submatchModule[5]))
		personA.Income = string(submatchModule[6])
	}
	fmt.Println("home:", personA.Home)
	fmt.Println("age:", personA.Age)
	fmt.Println("education:", personA.Education)
	fmt.Println("marriage:", personA.Marriage)
	fmt.Println("height:", personA.Height)

	//result := engine.ParseResult{nil, personA}
	result := engine.ParseResult{nil, []interface{}{personA}}
	return result
}

func commonCompileReg(contents []byte, regexp *regexp.Regexp) string {
	submatch := regexp.FindSubmatch(contents)

	return string(submatch[1])
}
