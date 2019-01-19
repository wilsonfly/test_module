package main

import (
	"testing"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	"fmt"
)

func TestHelloWorld(t *testing.T) {
	stub := shim.NewMockStub("hello", new(HelloWorld))

	checkInit(t, stub, [][]byte{[]byte("hw"), []byte("helloworld")})
	checkInvokeGet(t, stub, "hw")

	checkInvokeSet(t, stub, [][]byte{[]byte("set"), []byte("hw"), []byte("Newhelloworld")})
	checkInvokeGet(t, stub, "hw")

}

func checkInit(t *testing.T, stub *shim.MockStub, args [][]byte) {
	resp := stub.MockInit("1", args)
	if resp.Status != shim.OK {
		t.Error("init failed,", string(resp.Message))
	}
}

func checkInvokeSet(t *testing.T, stub *shim.MockStub, args [][]byte){
	resp := stub.MockInvoke("1", args)
	if resp.Status != shim.OK {
		t.Error("invoke set fail", resp.Message)
	}
}

func checkInvokeGet(t *testing.T, stub *shim.MockStub, name string){
	resp := stub.MockInvoke("1", [][]byte{[]byte("get"), []byte(name)})
	if resp.Status != shim.OK {
		t.Error("invok get fail")
	}
	fmt.Printf("get value:%s \n", resp.Payload)

}