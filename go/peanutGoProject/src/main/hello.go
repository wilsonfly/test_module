//package peanutProject
package main

import (
	"fmt"
	"time"
	"gx/ipfs/QmVmDhyTTUcQXFD1rRQ64fGLMSAoaQvNH3hwuaCFAPq2hy/errors"
	"runtime/debug"
	"bytes"
	"io"
	"os"
	"log"
)



func main() {
	fmt.Println("hello world")
	fmt.Println(3)

	//====begin====
	fmt.Println("no0 ========")

	s1 := []int{1, 2}
	s2 := []int{3, 4}
	//fmt.Println(append(s1,s2))
	fmt.Println(append(s1, s2...))
	//====end====

	//====begin====
	fmt.Println("no1 ========")

	var s []int
	s = append(s, 1)
	fmt.Println(s)
	//====end====

	//====begin====
	fmt.Println("no2 ========")

	arr := [5]int{1, 2, 3, 4, 5}
	fmt.Printf("arr cap:%d \n", cap(arr))

	slice := []int{1, 2, 3, 4, 5}
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

	for i := 0; i < 5; i++ {
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

	s3 := []string{"one", "two", "three"}
	for i, v := range s3 {
		fmt.Println(i, v)
		go func() {
			fmt.Println("in function:", i, v) //注意这个地方的打印，几个toroutine启动后都很快的打印出来
			time.Sleep(time.Second)
			fmt.Println(v)
		}()
	}
	time.Sleep(3 * time.Second) //如果没有这个sleep，func来不及执行
	//====end====

	//====begin====
	fmt.Println("no6 ====")
	s4 := []string{"one", "two", "three"}
	for v := range s4 {
		fmt.Println(v)
	}
	//====end====

	//====begin====
	fmt.Println("no7 ====")

	s5 := make(Slice, 0)
	defer s5.Add(1).Add(2) //TODO:??
	//s5.Add(3)
	fmt.Println(s5)
	//====end====

	//====begin====
	fmt.Println("no8 ========")
	type student struct {
		Name string
		Age  int
	}
	//m := make(map[string]int)
	students := []student{
		{Name: "aa", Age: 21},
		{Name: "bb", Age: 31},
		{Name: "cc", Age: 41},
	}
	for _, v := range students {
		fmt.Printf("no8, [%s,%d]\n", v.Name, v.Age)
		v.Age += 5 //修改的是副本
		fmt.Printf("no8, [%s,%d] \n", v.Name, v.Age)
	}
	for _, v := range students {
		fmt.Printf("no8, [%s,%d]\n", v.Name, v.Age)
	}

	for i := 0; i < len(students); i++ {
		students[i].Age += 5
	}
	for _, v := range students {
		fmt.Printf("no8, [%s,%d]\n", v.Name, v.Age)
	}
	//====end====

	//====begin====
	fmt.Println("no9 ========")
	for i := 0; i < 5; i++ {
		go func() {
			fmt.Printf("no9, %d\n", i) //555
		}()
	}
	time.Sleep(5)
	//====end====

	//====begin====
	fmt.Println("no10 ========")
	s6 := make([]int, 5)
	s6 = append(s6, 1, 2, 3)
	fmt.Println(s6)

	s7 := make([]int, 0)
	s7 = append(s7, 1, 2, 3)
	fmt.Println(s7)
	//====end====

	//====begin====
	fmt.Println("no11 ========")
	fmt.Println(constA, constB, constC, constD, constE, constF, constG)
	var varA int = 10
	var varB = 20
	fmt.Println(&varA, varA)
	fmt.Println(&varB, varB)
	//fmt.Println(&constA,constA) //cannot take the address of constA
	//====end====

	//====begin====
	fmt.Println("no12 ========")
	type MyInt1 int   //definition
	type MyInt2 = int //alias
	var i int = 9
	//var i1 MyInt1 = i //cannot use i (type int) as type MyInt1 in assignment
	var i1 MyInt1 = MyInt1(i)
	var i2 MyInt2 = i
	fmt.Println(i1, i2)
	//====end====

	//====begin====
	fmt.Println("no13 ========")
	fmt.Println(DoTheThing(true))
	fmt.Println(DoTheThing(false))
	fmt.Println(DoTheThing2(true))
	fmt.Println(DoTheThing2(false))

	//====end====

	//====begin====
	fmt.Println("no14 ========")
	FuncBiBao()
	FuncBiBao2()
	//====end====

	//====begin====
	fmt.Println("no15 ========")
	//testErr()
	//====end====

	//====begin====
	fmt.Println("no16 ========")
	var buf bytes.Buffer
	buf.Write([]byte("hello"))
	fmt.Fprintf(&buf,"world") //&buf
	//fmt.Println(buf)
	io.Copy(os.Stdout,&buf)  //&buf
	//====end====


	//====begin====
	fmt.Println("no17 ========")

	//====end====


	//====begin====
	fmt.Println("no ========")
	//====end====


	//====begin====
	fmt.Println("no ========")
	//====end====

	time.Sleep(3*time.Second)
}

type Slice []int

func (s *Slice) Add(v int) *Slice {
	*s = append(*s, v)
	return s
}

const (
	constA = iota
	_
	constB
	constC = "zz"
	constD
	constE = iota
	constF = 9
	constG
)

var ErrDidNotWork = errors.New("did not work")

func DoTheThing(reallyDoIt bool) (err error) {
	if reallyDoIt {
		//这个err作用域只限于if模块
		result, err := tryTheThing()
		if err != nil || result != "it worked" {
			err = ErrDidNotWork
		}
	}
	return err
}

func DoTheThing2(reallyDoIt bool) (err error) {
	var result string
	if reallyDoIt {
		//这个err是DoTheThing2全局的，由返回值处声明并且初始化为空值
		result, err = tryTheThing()
		if err != nil || result != "it worked" {
			err = ErrDidNotWork
		}
	}
	return err
}

func tryTheThing() (string, error) {
	return "", ErrDidNotWork
}

func FuncBiBao(){
	for i:=0;i<3;i++ {
		go func(){
			fmt.Println("funcbibao",i)
		}()

	}
}
func FuncBiBao2(){
	for i:=0;i<3;i++ {
		x := i   //TODO:不是重复声明么？x怎么就有三个不一样的值呢？
		go func(){
			fmt.Println("funcbibao2",x)
		}()
	}
}

func funcA() error {
	defer func() {
		if p := recover(); p != nil {
			fmt.Printf("panic recover! p: %v", p)
			debug.PrintStack()
		}
	}()
	return funcB()
}

func funcB() error {
	// simulation
	panic("foo")
	return errors.New("success")
}

func test() {
	err := funcA()
	if err == nil {
		fmt.Printf("err is nil\\n")
	} else {
		fmt.Printf("err is %v\\n", err)
	}
}

func funcC() error {
	defer func() {
		if p := recover(); p != nil {
			fmt.Printf("panic recover! p: %v", p)
			debug.PrintStack()
		}
	}()
	return funcD()
}

func funcD() error {
	// simulation
	panic("foo")
	return errors.New("success")
}

func testErr() {
	err := funcC()
	if err == nil {
		fmt.Printf("err is nil\\n")
	} else {
		fmt.Printf("err is %v\\n", err)
	}
}
