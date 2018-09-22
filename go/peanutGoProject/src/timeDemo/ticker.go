package main

import (
	"time"
	"fmt"
)

func main() {
	ticker := time.NewTicker(2* time.Second)
	//beginTime := time.Now().Unix()
	beginTime := time.Now().UnixNano()
	fmt.Println("Unix:",time.Now().Unix())
	fmt.Println("UnixNano:",time.Now().UnixNano())
	fmt.Println("6*second:",int64(6*time.Second))
	fmt.Println("second:",time.Second)

	for{
		<- ticker.C
		fmt.Println(time.Now().UnixNano())
		if time.Now().UnixNano() - beginTime > int64(6*time.Second) {
			fmt.Println("time up")
			break
		}
	}

}
