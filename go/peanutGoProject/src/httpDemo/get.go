package main

import (
	"os"
	"fmt"
	"net/http"
	"io"
)

func main() {
	if len(os.Args) != 3{
		fmt.Println("./examplle http://www.baidu.com filename")
		return
	}

	resp,err := http.Get(os.Args[1])
	if err!= nil{
		fmt.Println("failed to Get",os.Args[1])
		return
	}
	defer resp.Body.Close()

	file,err :=os.OpenFile(os.Args[2],os.O_RDWR|os.O_CREATE|os.O_APPEND,0666)
	//file,err := os.Create(os.Args[2])
	if err!= nil{
		fmt.Println("create file error")
		return
	}

	mw := io.MultiWriter(os.Stdout,file)
	io.Copy(mw,resp.Body)
	//fmt.Println(resp.Body)
}
