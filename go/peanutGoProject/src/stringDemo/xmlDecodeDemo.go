package main

import (
	"io/ioutil"
	"encoding/xml"
	"bytes"
	"fmt"
)

func main() {

	content, _ := ioutil.ReadFile("src/stringDemo/tokenColorings.xml")
	decoder := xml.NewDecoder(bytes.NewBuffer(content))

	var inFonts bool
	for t, err := decoder.Token(); err == nil; t, err = decoder.Token() {
		switch tocken := t.(type) {
		case xml.StartElement:
			name := tocken.Name.Local
			//fmt.Println(name)
			if inFonts {
				if name == "font6" {
					//fmt.Println(name)
					fmt.Println(getAttribute(tocken.Attr,"foreColor"))
				}
			} else {
				if name == "fonts" {
					inFonts = true
				}
			}
		case xml.EndElement:
			if inFonts {
				if tocken.Name.Local == "fonts" {
					inFonts = false
				}
			}
		}
	}

}

func getAttribute(attr []xml.Attr, name string) string {
	for _,a := range attr{
		if a.Name.Local == name{
			return a.Value
		}
	}
	return ""
}
