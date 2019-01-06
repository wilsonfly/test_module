package parser

import (
	"testing"
	"crawler/fetcher"
)

func TestParseCityList(t *testing.T) {
	bytes, e := fetcher.Fetch("http://www.zhenai.com/zhenghun")
	if e!= nil {
		panic(e)
	}

	ParseCityList(bytes)
}
