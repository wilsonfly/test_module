package fetcher

import (
	"net/http"
	"fmt"
	"io/ioutil"
	"bufio"
	"golang.org/x/text/transform"
	"io"
	"golang.org/x/text/encoding"
	"golang.org/x/net/html/charset"
	"golang.org/x/text/encoding/unicode"
)

func Fetch(url string) ([]byte, error) {
	resp, err := http.Get(url)
	if err != nil {
		return nil, err
	}
	defer resp.Body.Close()

	//response, _ := httputil.DumpResponse(resp, true)
	//fmt.Printf("%s", response)

	if resp.StatusCode != http.StatusOK {
		fmt.Println("Error statuscode ", resp.StatusCode)
		return nil, fmt.Errorf("Wrong statuscode %d", resp.StatusCode)
	}

	//可以将中文编码格式转换为参数指定的GBK等编码格式
	//reader := transform.NewReader(resp.Body, simplifiedchinese.GBK.NewDecoder())

	encoding := determineEncoding(resp.Body)
	transform.NewReader(resp.Body, encoding.NewDecoder())

	return ioutil.ReadAll(resp.Body)

	//fmt.Printf("%s\n", all)
}

func determineEncoding(r io.Reader) encoding.Encoding {
	bytes, e := bufio.NewReader(r).Peek(1024)
	if e != nil {
		fmt.Printf("determineEncoding error: %v", e)
		return unicode.UTF8
	}
	determineEncoding, _, _ := charset.DetermineEncoding(bytes, "")
	//fmt.Println("the encoding type:", determineEncoding)
	return determineEncoding
}
