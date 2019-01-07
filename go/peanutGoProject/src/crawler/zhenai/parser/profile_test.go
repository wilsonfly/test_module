package parser

import (
	"testing"
	"io/ioutil"
)

func TestParseProfile(t *testing.T) {

	bytes, e := ioutil.ReadFile("profile_testdata.html")
	if e != nil {
		t.Errorf("no testdata file")
	}
	ParseProfile(bytes)
}
