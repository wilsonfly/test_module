package main

import (
	"log"
	"net/http"
	"time"
	_ "net/http/pprof"
	"fmt"
)

//go run httpPprof.go &
//go tool pprof http://localhost:8090/debug/pprof/heap
func main() {
	go func(){
		log.Println(http.ListenAndServe("localhost:8090", nil))
	}()

	go funcA()
	go funcA()

	time.Sleep(60*time.Second)
}

func funcA()  {
	var num int
	for i:=0; i<1000000; i++{
		num += i
		time.Sleep(time.Millisecond)
	}
	fmt.Println(num)
}