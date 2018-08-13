package main

import (
	"sync"
	"fmt"
	"time"
	"math/rand"
)

var (
	tasks chan string
	wg    sync.WaitGroup
)

const (
	numTasks  = 10
	numWorker = 3
)

func init() {
	rand.Seed(time.Now().Unix())
}

func main() {
	tasks = make(chan string, numTasks)

	wg.Add(numWorker)
	for i := 0; i < numWorker; i++ {
		go doWork(i)
	}

	for i := 0; i < numTasks; i++ {
		tasks <- fmt.Sprintf("task-%d", i)
	}
	close(tasks) //因为是带缓冲的channel，这里关掉后仍然能从中读取出里边的内容

	wg.Wait()
}

func doWork(worker int) {
	defer wg.Done()
	for {
		task,ok := <-tasks
		if !ok {
			fmt.Printf("worker:%d finished \n",worker)
			return
		}

		fmt.Printf("worker:%d doing %s \n", worker, task)
		num := rand.Int63n(1000)
		//time.Sleep(num * time.Millisecond) //nvalid operation: num * time.Millisecond (mismatched types int64 and time.Duration)
		time.Sleep(time.Duration(num)*time.Millisecond)
	}
}
