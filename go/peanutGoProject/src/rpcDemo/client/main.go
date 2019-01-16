package main

import (
	"net"
	"net/rpc/jsonrpc"
	"fmt"
	"rpcDemo/service"
)

func main() {
	conn, e := net.Dial("tcp", ":1113")
	if e != nil {
		panic(e)
	}

	client := jsonrpc.NewClient(conn)

	var result float64
	err := client.Call("DemoService.Div", service.Args{A: 3, B: 2}, &result)
	fmt.Println(result, err)
	err = client.Call("DemoService.Div", service.Args{A: 2, B: 3}, &result)
	fmt.Println(result, err)
	err = client.Call("DemoService.Div", service.Args{A: 3, B: 0}, &result)//异常的时候result不会被更新，还是之前的值
	fmt.Println(result, err)
	err = client.Call("DemoService.Div", service.Args{A: 0, B: 2}, &result)
	fmt.Println(result, err)
	err = client.Call("abc.Div", service.Args{A: 3, B: 2}, &result)
	fmt.Println(result, err)

}
