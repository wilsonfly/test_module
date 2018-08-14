package main

import "fmt"

func main() {
	d := duration(20)
	d.funcA()  //使用变量的形式就是ok的！！！

	//duration(20).funcA()//cannot take the address of duration(20)
}


type duration int

func (d *duration)funcA(){
	fmt.Println("in funcA")
}