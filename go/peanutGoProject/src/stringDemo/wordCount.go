package main

import (
	"fmt"
	"io/ioutil"
	"strings"
	"os"
	"bufio"
)

func main() {
	filename := "src/stringDemo/data.txt"

	numWords := CountWords(filename)
	fmt.Println(numWords)

	numLines := CountLine(filename)
	fmt.Println(numLines)
}

func CountWords(filename string) int {
	contents, err := ioutil.ReadFile(filename)
	if err != nil {
		fmt.Println("open file error:", err)
		return -1
	}

	f := strings.Fields(string(contents)) //处理空格
	fmt.Println(f)

	num := len(f)
	return num
}

func CountLine(filename string) int {
	f, err := os.Open(filename)
	if err != nil {
		fmt.Println(err)
		return -1
	}
	defer f.Close()

	reader := bufio.NewReader(f)

	var lines int
	for {
		_, isprefix, err := reader.ReadLine()
		if err != nil {
			fmt.Println(err)
			break
		}

		if !isprefix {
			lines++
		}
	}
	return lines
}
