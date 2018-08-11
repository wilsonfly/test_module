package main

import (
	"sync"
	"fmt"
	"time"
)

var(
	ch chan int
	wg sync.WaitGroup
)

func main() {

	ch = make(chan int)

	wg.Add(1)
	go run()

	ch <- 1

	wg.Wait()
}

func run(){
	var newRunner int
	runner := <- ch


	fmt.Printf("player %d is running\n",runner)
	time.Sleep(2*time.Second)

	if runner==4{
		fmt.Println("complete the relay race")
		wg.Done()
		return
	}

	newRunner = runner+1
	go run()
	ch <- newRunner

}