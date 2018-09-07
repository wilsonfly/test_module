package main

import (
	"sort"
	"fmt"
)

func main() {
	originSort()
	mySort()
	personSort()
}

func originSort() {
	is := []int{1, 3, 2, 9, 22, 55, 32, 99, 45, 31, 9, 34, 3, 67}
	sort.Ints(is)
	fmt.Println(is)
	//sort.Reverse(is)
	//fmt.Println(is)

	ss := []string{"hello", "abc", "world", "hua", "sheng", "a"}
	sort.Strings(ss)
	fmt.Println(ss)
	//sort.Reverse(ss)
	//fmt.Println(ss)

}

type typeIntSlice []int

func (is typeIntSlice) Len() int           { /*return is.Len()*/ return len(is) }
func (is typeIntSlice) Swap(i, j int)      { is[i], is[j] = is[j], is[i] }
func (is typeIntSlice) Less(i, j int) bool { return is[i] < is[j] }
func mySort() {
	is := []int{1, 3, 2, 9, 22, 55, 32, 99, 45, 31, 9, 34, 3, 67}
	sort.Sort(typeIntSlice(is))
	fmt.Println(is)

	//sort.Reverse(typeIntSlice(is))
	sort.Sort(sort.Reverse(typeIntSlice(is))) //reverse之后还得加一个sort才行！！
	fmt.Println(is)
}

type person struct {
	Name string
	Age  int
}
type personSlice []person

func (s personSlice) Len() int           { return len(s) }
func (s personSlice) Swap(i, j int)      { s[i], s[j] = s[j], s[i] }
func (s personSlice) Less(i, j int) bool { return s[i].Age < s[j].Age }
func personSort() {
	//a := personSlice{{Name: "AAA", Age: 55,}, {Name: "BBB", Age: 22,}, {Name: "CCC", Age: 0,}, {Name: "mmm", Age: 22,}, {Name: "DDD", Age: 22,}, {Name: "EEE", Age: 11,},}
	a := personSlice{{Name: "AAA", Age: 55,}, {Name: "aaa", Age: 22,}, {Name: "BBB", Age: 22,}, {Name: "CCC", Age: 0,}, {Name: "mmm", Age: 22,}, {Name: "DDD", Age: 22,}, {Name: "EEE", Age: 11,},}
	sort.Sort(a)
	fmt.Println(a)

	sort.Stable(a)
	fmt.Println(a)

}
