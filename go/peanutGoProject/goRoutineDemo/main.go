package main

import (
	"runtime"
	"sync"
	"fmt"
	"time"
)

func main() {
	runtime.GOMAXPROCS(1)
	runtime.GOMAXPROCS(2)

	var wg sync.WaitGroup
	wg.Add(2)

	go func() {
		defer wg.Done()
		for i:=0;i<10;i++{
			for ch:='a';ch<='z';ch++{
				fmt.Printf("%c",ch)
				time.Sleep(7*time.Millisecond)
			}
			fmt.Println()
		}
	}()

	go func() {
		defer wg.Done()

		for i:=0;i<10;i++{
			for ch:='A';ch<='Z';ch++{
				fmt.Printf("%c",ch)
				time.Sleep(7*time.Millisecond)
			}
			fmt.Println()
		}
	}()

	wg.Wait()
}
