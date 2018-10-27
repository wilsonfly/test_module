package main

import (
	"io"
	"fmt"
	"os"
	"strings"
	"bufio"
)

func main() {
	//SampleReadFromString()
	//SampleReadFromStdin()
	SampleUseBufio()
}

func ReadFrom(reader io.Reader, n int) ([]byte, error) {
	p := make([]byte, n)
	n, err := reader.Read(p)
	if n > 0 {
		return p[:n], nil
	}
	return p, err
}

func SampleReadFromString() {
	r := strings.NewReader("hello world")
	data, _ := ReadFrom(r, 20)
	fmt.Println(data)
	fmt.Println(string(data))
}

func SampleReadFromStdin() {
	fmt.Println("input something:")
	data, _ := ReadFrom(os.Stdin, 20)
	fmt.Println(string(data))
}

func SampleUseBufio() {
	strReader := strings.NewReader("hello world")
	bufReader := bufio.NewReader(strReader)
	data, _ := bufReader.Peek(5) //返回缓存的一个切片，该切片引用缓存中前n字节数据
	fmt.Println(string(data))
	fmt.Println(bufReader.Buffered())

	str,_ := bufReader.ReadString('w')
	fmt.Println(string(str), bufReader.Buffered())

	w := bufio.NewWriter( os.Stdout)
	fmt.Fprint(w, "hello")
	fmt.Fprint(w, "www")
	w.Flush()
}
