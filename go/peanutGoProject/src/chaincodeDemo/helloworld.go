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
	fmt.Println("GetFunctionAndParameters:", fn, args)
	stringArgs := stub.GetStringArgs()
	fmt.Println("GetStringArgs:", stringArgs)

	switch fn {
	case "set":
		return h.set(stub, args)
	case "get":
		return h.get(stub, args)
	case "delete":
		return h.delete(stub, args)
	case "getByRange":
		return h.getByRange(stub, args)
	case "getHistory":
		return h.getHistory(stub, args)
	case "compositeKey":
		return h.compositeKey(stub, args)
	case "getQueryResult":
		return h.getQueryResult(stub, args)
		//case "InvokeChaincode":
		//TODO: call another chaincode
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

func (h *HelloWorld) delete(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	fmt.Println("====chaincodeDemo in delete")

	err := stub.DelState(args[0])
	if err != nil {
		return shim.Error(err.Error())
	}
	return shim.Success(nil)
}

func (h *HelloWorld) getByRange(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	fmt.Println("====chaincodeDemo in getByRange")

	//如果GetStateByRange传参startKey/endKey为""，则返回所有内容
	iterator, err := stub.GetStateByRange(args[0], args[1])
	if err != nil {
		return shim.Error(err.Error())
	}
	defer iterator.Close()

	result := []byte{}
	for iterator.HasNext() {
		kv, _ := iterator.Next()
		result = append(result, kv.Value...)
		fmt.Println("====chaincodeDemo in getByRange, result:", string(kv.Key), string(kv.Value))
	}
	return shim.Success(result)
}

func (h *HelloWorld) getHistory(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	fmt.Println("====chaincodeDemo in getHistory")

	iterator, err := stub.GetHistoryForKey(args[0])
	if err != nil {
		return shim.Error(err.Error())
	}
	defer iterator.Close()

	result := []byte{}
	for iterator.HasNext() {
		kv, _ := iterator.Next()
		result = append(result, kv.Value...)
		fmt.Println("====chaincodeDemo in getHistory, result:", string(kv.TxId), string(kv.Value))
		fmt.Println("====chaincodeDemo in getHistory, result:", string(kv.String()))
	}
	return shim.Success(result)
}

func (h *HelloWorld) compositeKey(stub shim.ChaincodeStubInterface, args []string) peer.Response {
	fmt.Println("====chaincodeDemo in compositeKey")

	indexName := "sexName"
	indexKey, _ := stub.CreateCompositeKey(indexName, []string{"boy", "xiaoming"})
	fmt.Println(indexKey)
	stub.PutState(indexKey, []byte("hello"))

	indexKey, _ = stub.CreateCompositeKey(indexName, []string{"boy", "xiaohua"})
	fmt.Println(indexKey)
	stub.PutState(indexKey, []byte("hello"))

	indexKey, _ = stub.CreateCompositeKey(indexName, []string{"girl", "xiaoli"})
	fmt.Println(indexKey)
	stub.PutState(indexKey, []byte("hello"))

	iterator, _ := stub.GetStateByPartialCompositeKey(indexName, []string{"boy"})
	defer iterator.Close()

	for iterator.HasNext() {
		kv, _ := iterator.Next()
		name, parts, _ := stub.SplitCompositeKey(kv.Key)
		fmt.Println("indexName:", name)
		fmt.Println("parts[0]:", parts[0])
		fmt.Println("parts[1]:", parts[1])
	}

	return shim.Success(nil)
}

func (h *HelloWorld)getQueryResult(stub shim.ChaincodeStubInterface, args []string) peer.Response  {
	fmt.Println("====chaincodeDemo in getQueryResult")

	iterator, err := stub.GetQueryResult("{\"selector\":{\"sexName\":\"girl\"}}")
	if err != nil {
		return shim.Error("getqueryresult failed")
	}

	defer iterator.Close()

	for iterator.HasNext() {
		kv, _ := iterator.Next()
		fmt.Println(kv)
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
