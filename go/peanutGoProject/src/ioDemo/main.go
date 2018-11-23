package main

import (
	"io"
	"fmt"
	"os"
	"strings"
	"bufio"
	"encoding/binary"
)

func main() {

	if len(os.Args) >= 2 {
		filename := os.Args[1]
		SampleCountLineNum2(filename)
		SampleCountLineNum(filename)
		return
	}

	//SampleReadFromString()
	//SampleReadFromStdin()
	//SampleUseBufio()
	SampleReadBitmap()
	SampleReadBitmap2()

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

	str, _ := bufReader.ReadString('w')
	fmt.Println(string(str), bufReader.Buffered())

	w := bufio.NewWriter(os.Stdout)
	fmt.Fprint(w, "hello")
	fmt.Fprint(w, "www")
	w.Flush()
}

func SampleCountLineNum(filename string) {
	f, err := os.Open(filename)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer f.Close()
	r := bufio.NewReader(f)

	var line uint64
	for {
		_, isPrefix, err := r.ReadLine()
		if err != nil {
			break
		}
		if !isPrefix {
			line++
		}
	}
	fmt.Println("line:", line)
}

func SampleCountLineNum2(filename string) {
	f, err := os.Open(filename)
	if err != nil {
		fmt.Println(err)
		return
	}
	s := bufio.NewScanner(f)
	for s.Scan(){
		fmt.Println(s.Text())
	}
}

/*
https://msdn.microsoft.com/zh-cn/library/aa930979.aspx

typedef struct tagBITMAPFILEHEADER {
	WORD bfType;
	DWORD bfSize;
	WORD bfReserved1;
	WORD bfReserved2;
	DWORD bfOffBits;
} BITMAPFILEHEADER;
*/
func SampleReadBitmap() {
	//file,err := os.Open("image.bmp")
	file, err := os.Open("src/ioDemo/image.bmp")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()

	var headA, headB byte
	binary.Read(file, binary.LittleEndian, &headA) //windows/linux 小端, mac 大端
	binary.Read(file, binary.LittleEndian, &headB)
	fmt.Println(headA, headB)
	fmt.Printf("%c,%c \n", headA, headB)

	var size uint32
	binary.Read(file, binary.LittleEndian, &size)

	var resv1, resv2 uint16
	binary.Read(file, binary.LittleEndian, &resv1)
	binary.Read(file, binary.LittleEndian, &resv2)

	var offbit uint32
	binary.Read(file, binary.LittleEndian, &offbit)

	fmt.Println(headA, headB, size, resv1, resv2, offbit)

}

//panic: reflect: reflect.Value.SetUint using value obtained using unexported field
//type BitmapFileHeader struct {
//	bfType      uint16
//	bfSize      uint32
//	bfReserved1 uint16
//	bfReserved2 uint16
//	bfOffBits   uint32
//}

type BitmapFileHeader struct {
	Type      uint16
	Size      uint32
	Reserved1 uint16
	Reserved2 uint16
	OffBits   uint32
}

func SampleReadBitmap2() {
	file, err := os.Open("src/ioDemo/image.bmp")
	if err != nil {
		fmt.Println(err)
		return
	}
	defer file.Close()

	bfHeader := new(BitmapFileHeader)
	binary.Read(file, binary.LittleEndian, bfHeader)

	fmt.Println(bfHeader)
}
