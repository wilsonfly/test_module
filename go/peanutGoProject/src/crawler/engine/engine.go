package engine

import (
	"crawler/fetcher"
	"fmt"
)

func Run(seeds ...ParseRequest) {
	var requests []ParseRequest
	for _, r := range seeds {
		requests = append(requests, r)
	}

	for len(requests) > 0 {
		r := requests[0]
		requests = requests[1:]

		parseResult, e := worker(requests)
		if e != nil {
			continue
		}
		requests = append(requests, parseResult.Requests...)

		for _, item := range parseResult.Items {
			fmt.Println("in Run, Got item:", item)
		}
	}
}

func worker(r ParseRequest) (ParseResult,error) {
	fmt.Println("Fetching url:", r.Url)

	bytes, e := fetcher.Fetch(r.Url)
	if e!= nil {
		fmt.Printf("fetcher error, url:%v, err:%v", r.Url, e)
		return ParseResult{}, nil
	}

	return r.ParseFunc(bytes), nil
}