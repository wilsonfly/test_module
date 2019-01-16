package main

import (
	"net/rpc"
	"net"
	"log"
	"net/rpc/jsonrpc"
	"rpcDemo/service"
)

/*
终端上执行：telnet localhost 1111
输入json数据
{"method":"abc.def"}
{"method":"DemoService.Div"}
{"method":"DemoService.Div","params":[{"A":3,"B":2}],"id":1}
{"method":"DemoService.Div","params":[{"A":2,"B":3}],"id":2}
{"method":"DemoService.Div","params":[{"A":2,"B":0}],"id":2}
{"method":"DemoService.Div","params":[{"A":2}],"id":2}
{"method":"DemoService.Div","params":[{"B":2}],"id":2}

结果：
{"method":"abc.def"}
{"id":null,"result":null,"error":"rpc: can't find service abc.def"}
{"method":"DemoService.Div"}
{"id":null,"result":null,"error":"jsonrpc: request body missing params"}
{"method":"DemoService.Div","params":[{"A":3,"B":2}],"id":1}
{"id":1,"result":1.5,"error":null}
{"method":"DemoService.Div","params":[{"A":2,"B":3}],"id":2}
{"id":2,"result":0.6666666666666666,"error":null}
{"method":"DemoService.Div","params":[{"A":2,"B":0}],"id":2}
{"id":2,"result":null,"error":"try to div by zero"}
{"method":"DemoService.Div","params":[{"A":2}],"id":2}
{"id":2,"result":null,"error":"try to div by zero"}
{"method":"DemoService.Div","params":[{"B":2}],"id":2}
{"id":2,"result":0,"error":null}
quit
Connection closed by foreign host.

 */

func main() {
	rpc.Register(service.DemoService{})
	listener, err := net.Listen("tcp", ":1113")
	if err != nil {
		panic(err)
	}

	for {
		conn, e := listener.Accept()
		if e != nil {
			log.Printf("accept error:%v", e)
			continue
		}

		go jsonrpc.ServeConn(conn)

	}
}
