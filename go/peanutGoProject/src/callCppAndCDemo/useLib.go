package main

/*
//#cgo CFLAGS: -IcallCppAndCDemo/include
//#cgo LDFLAGS: -LcallCppAndCDemo/libcode -llibadd

#cgo CFLAGS: -Iinclude
#cgo LDFLAGS: -Llibcode -ladd
#include "add.h"
 */
import "C"
import "fmt"

func main() {
	fmt.Println(C.add(2,3))
}

/*
终端上配置此环境变量，否则会有如下报错：
dyld: Library not loaded: libadd.so
Referenced from: /var/folders/p3/nk9v6c652s5_jtmtw9c94_n00000gn/T/go-build136812062/b001/exe/useLib
Reason: image not found
signal: abort trap
*/
//export DYLD_LIBRARY_PATH=/Users/peanut/projects/test_module/go/peanutGoProject/src/callCppAndCDemo/libcode