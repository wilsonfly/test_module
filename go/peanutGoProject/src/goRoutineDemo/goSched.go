package main

import (
	"sync"
	"runtime"
	"fmt"
	"sync/atomic"
	"time"
)

var (
	sum int64
	wg sync.WaitGroup
	stop int64
)

func main() {

	//runtime.GOMAXPROCS(1) //实际运行结果2、3交替出现
	//不限制maxproc为1的话，大部分情况下是4，六分之一左右的概率会出现2

	wg.Add(4)
	//go Add()
	//go Add()
	go Add2()
	go Add2()

	go funcLoad()
	go funcStore()

	wg.Wait()
	fmt.Println(sum)
}

func Add(){
	defer wg.Done()

	var tmp int64
	for i:=0;i<2;i++{
		tmp = sum
		runtime.Gosched()
		tmp++
		sum = tmp
	}
}

func Add2(){
	defer wg.Done()

	for i:=0;i<2;i++{
		atomic.AddInt64(&sum,1)
		runtime.Gosched()
	}
}

func funcLoad(){
	defer wg.Done()
	fmt.Println("funcLoad begin")

	for{
		fmt.Println("funcLoad sleep")
		time.Sleep(500*time.Millisecond)
		num := atomic.LoadInt64(&stop)
		if num==1{
			break
		}
	}

	fmt.Println("funcLoad end")
}

func funcStore(){
	defer wg.Done()

	fmt.Println("funcStore begin")
	time.Sleep(2*time.Second)
	atomic.StoreInt64(&stop,1)
	fmt.Println("funcStore end")
}