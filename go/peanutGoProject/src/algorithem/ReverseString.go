package main

import "fmt"

func Reverse(s string) string {
	t := []byte(s) //this is the point
	for i := 0; i < len(s)/2; i++ {
		j := len(s) - 1 - i
		t[i], t[j] = t[j], t[i]
	}
	return string(t)
}

func Reverse2(s string) string {
	a := []byte(s)
	for i, j := 0, len(a)-1; i < j; i, j = i+1, j-1 {
		a[i], a[j] = a[j], a[i]
	}
	return string(a)
}

func main() {
	//s := []string{"Hello world!"}
	s := Reverse("Hello world!")
	fmt.Println(s)

	a := "Hello go!"
	result := Reverse(a)
	fmt.Println(result)

	fmt.Println(Reverse2(a))
}
