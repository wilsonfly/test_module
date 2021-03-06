package parser

import (
	"crawler/engine"
	"regexp"
	"fmt"
)

var maxCityNumForTest = 1

func ParseCityList(contents []byte) engine.ParseResult {
	//<a target="_blank" href="http://www.zhenai.com/zhenghun/tianjin" data-v-4e064b2c>天津</a>
	//<a href="http://www.zhenai.com/zhenghun/changchun" data-v-473e2ba0>长春>
	compile := regexp.MustCompile(`<[^>]+href="(http://www.zhenai.com/zhenghun/[0-9a-z]+)"[^>]+>([^<]+)</a>`)

	//all := compile.FindAll(contents, -1)
	//for _, m := range all {
	//	fmt.Printf("%s\n", m)
	//}

	result := engine.ParseResult{}
	submatch := compile.FindAllSubmatch(contents, -1)
	for _, m := range submatch {
		result.Requests = append(result.Requests, engine.ParseRequest{Url: string(m[1]), ParseFunc: ParseCityUserList})
		result.Items = append(result.Items, string(m[2]))
		fmt.Printf("in ParseCityList, get url:%s, cityname:%s\n", m[1], m[2])

		maxCityNumForTest--
		if maxCityNumForTest <= 0 {
			break
		}
	}

	fmt.Println("in ParseCityList, get city num:", len(result.Items))

	return result
}
