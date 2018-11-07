package main

import (
	"flag"
	"fmt"
)

/*
./flagsDemo -name huasheng -age 18
./flagsDemo -name=huasheng -age=18
*/

func main() {
	//sample1()
	sample2()
}

func sample1() {
	var namePtr *string
	var agePtr *int
	namePtr = flag.String("name", "peanut", "input one name")
	agePtr = flag.Int("age", -1, "input age")
	flag.Parse()
	fmt.Println(*namePtr, *agePtr)
}

func sample2() {
	var name string
	var age int
	flag.StringVar(&name, "name", "peanut", "input one name")
	flag.IntVar(&age, "age", -1, "input age")
	flag.Parse()
	fmt.Println(name, age)
}
