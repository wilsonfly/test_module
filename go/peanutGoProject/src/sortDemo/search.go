package main

import (
	"fmt"
	"sort"
)

func main() {
	//GuessingGame()
	intSearch()
}

func GuessingGame() {
	var s string
	fmt.Printf("Pick an integer from 0 to 100.\n")
	answer := sort.Search(100, func(i int) bool {
		fmt.Printf("Is your number <= %d? ", i)
		fmt.Scanf("%s", &s)
		return s != "" && s[0] == 'y'
	})
	fmt.Printf("Your number is %d.\n", answer)
}

func intSearch() {
	is := []int{ 33, 22, 44, 11, 55}
	sort.Ints(is)
	fmt.Println(is)

	result := sort.SearchInts(is, 30)
	fmt.Println(result) //结果为2，可插入的位置！！！

	result = sort.SearchInts(is, 33)
	fmt.Println(result)

	result = sort.Search(len(is), func(i int) bool { return is[i] >= 100 })
	fmt.Println(result)//5，查找不到，返回 slice的长度５，而不是-1

	result = sort.Search(len(is), func(i int) bool { return is[i] == 33 })
	fmt.Println(result)

	result = sort.Search(len(is), func(i int) bool { return is[i] <= 33 })
	fmt.Println(result)//0，利用二分法进行查找，返回符合条件的最左边数值的index，即为０


}
