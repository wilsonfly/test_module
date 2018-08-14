package main

import (
	"fmt"
	"sync"
)

var wg sync.WaitGroup

func main() {
	ch := make(chan int)
	go funcA(ch)
	wg.Add(1)

	for i := 1; i < 10; i++ {
		ch <- i
	}

	close(ch) //如果没有close，也能打印出所有数据，但是会有异常：fatal error: all goroutines are asleep - deadlock!
	fmt.Println("waiting")
	wg.Wait()

}

func funcA(c chan int) {
	for n := range c {
		fmt.Println(n)
	}
	wg.Done()
}
