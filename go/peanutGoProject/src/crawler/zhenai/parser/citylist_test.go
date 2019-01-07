package parser

import (
	"testing"
	"io/ioutil"
)

const itemNum = 494

func TestParseCityList(t *testing.T) {
	//bytes, e := fetcher.Fetch("http://www.zhenai.com/zhenghun")
	bytes, e := ioutil.ReadFile("citylist_testdata.html")
	if e != nil {
		panic(e)
	}

	//fmt.Printf("%s", bytes)

	parseResult := ParseCityList(bytes)
	if len(parseResult.Items) != itemNum {
		t.Errorf("citynum should have:%d, but got:%d", itemNum, len(parseResult.Items))
	}

	expectUrls := []string{
		"http://www.zhenai.com/zhenghun/aba",
		"http://www.zhenai.com/zhenghun/akesu",
		"http://www.zhenai.com/zhenghun/alashanmeng",
	}
	expectCities := []string{
		"阿坝",
		"阿克苏",
		"阿拉善盟",
	}

	for i, url := range expectUrls {
		if parseResult.Requests[i].Url != url {
			t.Errorf("expected url %d: %s, but get url: %s", i, url, parseResult.Requests[i].Url)
		}
	}

	for i, city := range expectCities {
		if parseResult.Items[i].(string) != city {
			t.Errorf("expected url %d: %s, but get url: %s", i, city, parseResult.Items[i])
		}
	}


}
