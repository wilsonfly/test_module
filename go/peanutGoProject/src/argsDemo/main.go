package main

import (
	"os"
	"fmt"
	"net/http"
	"io"
)

func init() {
	if len(os.Args) != 2 {
		fmt.Println("Usage: ./example url")
		os.Exit(-1)
	}
}

func main() {
	r, err := http.Get(os.Args[1])
	if err != nil {
		fmt.Println(err)
		return
	}
	io.Copy(os.Stdout, r.Body)

	//TODO:how to read?
	//var s []byte
	//num,err :=r.Body.Read(s)
	//fmt.Println(num,err)
	//if err!=nil{
	//	fmt.Println(num,err,string(s))
	//}


	if err = r.Body.Close(); err != nil {
		fmt.Println(err)
	}
}
