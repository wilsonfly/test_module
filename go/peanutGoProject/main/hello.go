//package peanutProject
package main

import (
	"fmt"
	"time"
)

func main()  {
	fmt.Println("hello world")
	fmt.Println(3)

	//====begin====
	s1 := []int{1,2}
	s2 := []int{3,4}
	//fmt.Println(append(s1,s2))
	fmt.Println(append(s1,s2...))
	//====end====


	//====begin====
	var s []int
	s = append(s,1)
	fmt.Println(s)
	//====end====


	//====begin====
	arr := [5]int{1,2,3,4,5}
	fmt.Printf("arr cap:%d \n", cap(arr))

	slice := []int{1,2,3,4,5}
	fmt.Printf("slice cap:%d \n", cap(slice))

	/*
	map donot have member:cap
	m := map[string]int{"a":1,"b":2}
	fmt.Printf("slice cap:%d \n", cap(m))
	 */

	c := make(chan int)
	fmt.Printf("channel cap:%d \n", cap(c))
	//====end====


	//====begin====
	for i:=0;i<5;i++ {
		defer fmt.Printf("%d ", i)
	}
	//====end====


	//====begin====
	domain1 := 1
	{
		domain1 := 2
		fmt.Print(domain1)
	}
	fmt.Println(domain1)
	//====end====


	//====begin====
	s4 := []string{"one","two","three"}
	for v := range s4{
		fmt.Println(v)
	}
	//====end====


	//====begin====
	s3 := []string{"one","two","three"}
	for i,v := range s3 {
		fmt.Println(i,v)
		go func() {
			fmt.Println("in function:",i,v) //注意这个地方的打印，几个toroutine启动后都很快的打印出来
			time.Sleep(time.Second)
			fmt.Println(v)
		}()
	}
	time.Sleep(3*time.Second)//如果没有这个sleep，func来不及执行
	//====end====



	//====begin====
	type Slice []int
	func (s *Slice)Add(v int){
	*s =  s.append(v)
	}
	s5 := make(Slice,0)
	defer s5.Add(1).Add(2)
	s5.Add(3)
	fmt.Println(s)
	//====end====

	//====begin====
	//====end====

	//====begin====
	//====end====

}
