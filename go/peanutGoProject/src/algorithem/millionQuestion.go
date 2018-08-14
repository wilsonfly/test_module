package main

import "fmt"

func main() {
    a := 0.01
    b := 100000

    for i:=0;i<30;i++ {
        a *= 2
        b += 100000
    }

    fmt.Println(a,b)
}
