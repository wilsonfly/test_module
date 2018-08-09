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
	fmt.Println("no0 ========")

	s1 := []int{1,2}
	s2 := []int{3,4}
	//fmt.Println(append(s1,s2))
	fmt.Println(append(s1,s2...))
	//====end====


	//====begin====
	fmt.Println("no1 ========")

	var s []int
	s = append(s,1)
	fmt.Println(s)
	//====end====


	//====begin====
	fmt.Println("no2 ========")

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
	fmt.Println("no3 ========")

	for i:=0;i<5;i++ {
		defer fmt.Printf("no3,defer num:%d \n", i)
	}
	//====end====


	//====begin====
	fmt.Println("no4 ========")

	domain1 := 1
	{
		domain1 := 2
		fmt.Print(domain1)
	}
	fmt.Println(domain1)
	//====end====


	//====begin====
	fmt.Println("no5 ========")

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
	fmt.Println("no6 ====")
	s4 := []string{"one","two","three"}
	for v := range s4{
		fmt.Println(v)
	}
	//====end====


	//====begin====
	fmt.Println("no7 ====")

	s5 := make(Slice,0)
	defer s5.Add(1).Add(2)   //TODO:??
	//s5.Add(3)
	fmt.Println(s5)
	//====end====

	//====begin====
	fmt.Println("no8 ========")
	type student struct {
		Name string
		Age int
	}
	//m := make(map[string]int)
	students := []student{
		{Name:"aa",Age:21},
		{Name:"bb",Age:31},
		{Name:"cc",Age:41},
	}
	for _,v := range students{
		fmt.Printf("no8, [%s,%d]\n",v.Name,v.Age)
		v.Age += 5    //修改的是副本
		fmt.Printf("no8, [%s,%d] \n",v.Name,v.Age)
	}
	for _,v := range students{
		fmt.Printf("no8, [%s,%d]\n",v.Name,v.Age)
	}

	for i:=0; i< len(students); i++ {
		students[i].Age += 5
	}
	for _,v := range students{
		fmt.Printf("no8, [%s,%d]\n",v.Name,v.Age)
	}
	//====end====


	//====begin====
	fmt.Println("no9 ========")
	for i:=0; i<5; i++{
		go func(){
			fmt.Printf("no9, %d\n",i)   //555
		}()
	}
	time.Sleep(5)
	//====end====


	//====begin====
	fmt.Println("no10 ========")
	s6 := make([]int,5)
	s6 = append(s6, 1,2,3)
	fmt.Println(s6)

	s7 := make([]int,0)
	s7 = append(s7, 1,2,3)
	fmt.Println(s7)
	//====end====

	//====begin====
	fmt.Println("no111 ========")

	//====end====

	//====begin====
	fmt.Println("no ========")
	//====end====

	//====begin====
	fmt.Println("no ========")
	//====end====
}

type Slice []int
func (s *Slice) Add(v int) *Slice {
	*s = append(*s, v)
	return s
}