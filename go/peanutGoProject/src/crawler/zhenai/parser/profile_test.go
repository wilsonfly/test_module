package parser

import (
	"testing"
	"io/ioutil"
	"crawler/model"
	"fmt"
)

func TestParseProfile(t *testing.T) {

	bytes, e := ioutil.ReadFile("profile_testdata.html")
	if e != nil {
		t.Errorf("no testdata file")
	}
	result := ParseProfile(bytes, "====somename,todo====")

	personB := result.Items[0].(model.Personal)

	//nickName: 靖雯
	//ID: 1621215509
	//home: 合肥
	//age: 24
	//education: 大学本科
	//marriage: 未婚
	//height: 163
	//income: 5001-8000元

	expectedB := model.Personal{
		Name:      "靖雯",
		Id:        "1621215509",
		Home:      "合肥",
		Age:       24,
		Education: "大学本科",
		Marriage:  "未婚",
		Height:    163,
		Income:    "5001-8000元",
	}

	if personB != expectedB {
		t.Errorf("expected %v, but get %v", expectedB, personB)
	}
	fmt.Println(personB)

}
