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

	personal := model.Personal{}

	//compile := regexp.MustCompile(nickNameReg)
	//submatch := compile.FindSubmatch(contents)
	//if submatch!= nil {
	//	personal.Name = string(submatch[1])
	//}
	//fmt.Println(personal.Name)

	nickName := commonCompileReg(contents, nickNameRegPreCompile)
	personal.Name = nickName
	fmt.Println(nickName)

	//compileID := regexp.MustCompile(idReg)
	//submatchID := compileID.FindSubmatch(contents)
	//if submatchID != nil {
	//	personal.Id = string(submatchID[1])
	//}
	//fmt.Println(personal.Id)
	id := commonCompileReg(contents, idRegPreCompile)
	personal.Id = id
	fmt.Println(id)

	compileModule := regexp.MustCompile(moduleReg)
	submatchModule := compileModule.FindSubmatch(contents)
	if submatchModule != nil {
		personal.Home = string(submatchModule[1])
		personal.Age, _ = strconv.Atoi(string(submatchModule[2]))
		personal.Education = string(submatchModule[3])
		personal.Marriage = string(submatchModule[4])
		personal.Height, _ = strconv.Atoi(string(submatchModule[5]))
		personal.Income = string(submatchModule[6])
	}
	fmt.Println("home:", personal.Home)
	fmt.Println("age:", personal.Age)
	fmt.Println("education:", personal.Education)
	fmt.Println("marriage:", personal.Marriage)
	fmt.Println("height:", personal.Height)

	return engine.NilParser(nil)
}

func commonCompileReg(contents []byte, regexp *regexp.Regexp) string {
	submatch := regexp.FindSubmatch(contents)

	return string(submatch[1])
}
