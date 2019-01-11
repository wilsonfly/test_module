package parser

import (
	"testing"
	"io/ioutil"
)

func TestParseCityUserList(t *testing.T) {
	bytes, e := ioutil.ReadFile("cityuserlist_testdata.html")
	if e != nil {
		panic(e)
	}

	userList := ParseCityUserList(bytes)

	//http://album.zhenai.com/u/103526880 车厘子
	//http://album.zhenai.com/u/36528631 映山红1968
	//http://album.zhenai.com/u/41815704 sweety
	expectUrls := []string{
		//"xxx",
		"http://album.zhenai.com/u/103526880",
		"http://album.zhenai.com/u/36528631",
		"http://album.zhenai.com/u/41815704",
	}

	for i, u := range expectUrls {
		if u != userList.Requests[i].Url {
			t.Errorf("expect %s, but get:%s", u, userList.Requests[i].Url)
		}
	}
}
