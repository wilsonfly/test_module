package main

import (
	"github.com/hyperledger/fabric/core/chaincode/shim"
	"fmt"
	"github.com/hyperledger/fabric/protos/peer"
)

type HelloWorld struct {
}

func (h *HelloWorld) Init(stub shim.ChaincodeStubInterface) peer.Response {
	fmt.Println("====chaincodeDemo in Init")

	args := stub.GetStringArgs()
	err := stub.PutState(args[0], []byte(args[1]))
	if err != nil {
		return shim.Error(err.Error())
	}
	return shim.Success(nil)
}

func (h *HelloWorld) Invoke(stub shim.ChaincodeStubInterface) peer.Response {
	fmt.Println("====chaincodeDemo in Invoke")

	fn, args := stub.GetFunctionAndParameters()

	switch fn {
	case "set":
		return h.set(stub, args)
	case "get":
		return h.get(stub, args)
	}
	return shim.Error("Invoke failed")
}

func (h *HelloWorld) get(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	fmt.Println("====chaincodeDemo in get")

	bytes, err := stub.GetState(args[0])
	if err != nil {
		return shim.Error(err.Error())
	}
	fmt.Println("====chaincodeDemo in get, result:", string(bytes))
	return shim.Success(bytes)
}

func (h *HelloWorld) set(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	fmt.Println("====chaincodeDemo in set")

	err := stub.PutState(args[0], []byte(args[1]))
	if err != nil {
		return shim.Error(err.Error())
	}
	return shim.Success(nil)
}

func main() {
	fmt.Println("====chaincodeDemo in main")

	err := shim.Start(new(HelloWorld))
	if err != nil {
		fmt.Println("fail to start chaincode")
	}
}
