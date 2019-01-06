package main

import (
	"crawler/engine"
	"crawler/zhenai/parser"
)

func main() {

	engine.Run(engine.ParseRequest{
		Url:       "http://www.zhenai.com/zhenghun",
		ParseFunc: parser.ParseCityList},
	)
}
