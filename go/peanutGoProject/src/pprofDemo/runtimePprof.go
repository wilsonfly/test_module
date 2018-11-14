package main

import (
	"os"
	"fmt"
	"runtime/pprof"
)

//go tool pprof runtimePprof cpuprofile.prof

func main() {

	f, err := os.Create("./cpuprofile.prof")
	if f == nil {
		fmt.Println(err)
		return
	}
	defer f.Close()

	pprof.StartCPUProfile(f)
	defer pprof.StopCPUProfile()

	for i := 0; i < 30; i++ {
		num := fibonacci(i)
		fmt.Println(num)
	}
}

func fibonacci(n int) int {
	if n < 2 {
		return 1
	}
	return fibonacci(n-1) + fibonacci(n-2)
}
