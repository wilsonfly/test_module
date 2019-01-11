package parser

import (
	"crawler/engine"
	"regexp"
	"fmt"
)

var maxUserNumForTest = 3

//<a href="http://album.zhenai.com/u/103526880" target="_blank">车厘子</a>
var cityUserListReg = regexp.MustCompile(`<a href="(http://album.zhenai.com/u/[0-9]+)" target="_blank">([^<]+)</a>`)

func ParseCityUserList(contents []byte) engine.ParseResult {
	submatch := cityUserListReg.FindAllSubmatch(contents, -1)
	result := engine.ParseResult{}
	for _, s := range submatch {
		url := string(s[1])
		name := string(s[2])
		fmt.Println("in ParseCityUserList,", url, name)

		result.Requests = append(result.Requests, engine.ParseRequest{url, func(bytes []byte) engine.ParseResult {
			return ParseProfile(bytes, name)
		}})

		maxUserNumForTest--
		if maxUserNumForTest <=0 {
			break;
		}
	}
	fmt.Println("in ParseCityUserList, get user num:", len(result.Requests))
	return result
}
