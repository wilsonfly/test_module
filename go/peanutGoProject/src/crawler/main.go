package main

import (
	"net/http"
	"io/ioutil"
	"fmt"
	"golang.org/x/net/html/charset"
	"golang.org/x/text/encoding"
	"bufio"
	"golang.org/x/text/transform"
	"io"
)

func main() {
	resp, err := http.Get("http://www.zhenai.com/zhenghun")
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()

	//response, _ := httputil.DumpResponse(resp, true)
	//fmt.Printf("%s", response)

	if resp.StatusCode != http.StatusOK {
		fmt.Println("Error statuscode ", resp.StatusCode)
		return
	}

	//可以将中文编码格式转换为参数指定的GBK等编码格式
	//reader := transform.NewReader(resp.Body, simplifiedchinese.GBK.NewDecoder())

	encoding := determineEncoding(resp.Body)
	transform.NewReader(resp.Body, encoding.NewDecoder())

	all, e := ioutil.ReadAll(resp.Body)
	if e != nil {
		panic(e)
	}
	fmt.Printf("%s\n", all)
}

func determineEncoding(r io.Reader) encoding.Encoding {
	bytes, e := bufio.NewReader(r).Peek(1024)
	if e != nil {
		panic(e)
	}
	determineEncoding, _, _ := charset.DetermineEncoding(bytes, "")
	fmt.Println("the encoding type:", determineEncoding)
	return determineEncoding
}
