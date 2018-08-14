package main

import (
	"sync"
	"fmt"
	"math/rand"
	"time"
)

var (
	ch chan int
	wg sync.WaitGroup
)

func init() {
	rand.Seed(time.Now().Unix())
}

func main() {

	ch = make(chan int)

	wg.Add(2)
	go play("playerA")
	go play("playerB")

	//start the game
	ch <- 1

	wg.Wait()
}

func play(p string) {
	defer wg.Done()
	for {
		ball, ok := <-ch
		if !ok {
			//channel is closed
			fmt.Println(p, "win the game")
			return
		}

		n := rand.Int63n(100)
		//n := rand.Intn(100)
		if n%13 == 0 {
			fmt.Println(p, "miss the ball")
			close(ch)
			return
		}

		fmt.Println(p,"kick the ball")
		ball++
		ch <- ball
	}
}
