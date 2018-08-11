package main

import (
	"fmt"
)

func main() {
	printPrimeNumber(23)
}

func printPrimeNumber(max int) {

//next:  //invalid continue label next,continue的label需要紧跟着循环
	fmt.Println("next")
next:
	for outer := 2; outer <= max; outer++ {
		for inner := 2; inner < outer; inner++ {
			if outer%inner == 0 {
				//goto next
				continue next
			}
		}
		fmt.Println(outer)
	}

}
