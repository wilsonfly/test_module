package main

import (
	"log"
	"fmt"
	"io/ioutil"
	"os"
	"io"
)

var (
	Trace   *log.Logger
	Info    *log.Logger
	Warning *log.Logger
	Error   *log.Logger
)

func init() {
	log.SetPrefix("[huasheng]")
	log.SetFlags(log.Ldate | log.Lmicroseconds | log.Lshortfile)

	file, err := os.OpenFile("logDemo/error.log", os.O_CREATE|os.O_WRONLY|os.O_APPEND, 0666)
	if err != nil {
		log.Fatalln("fail to open file")
	}
	Trace = log.New(ioutil.Discard, "Trace:", log.Ldate|log.Lmicroseconds|log.Lshortfile)
	Info = log.New(os.Stdout, "Info:", log.Ldate|log.Lmicroseconds|log.Lshortfile)
	Warning = log.New(os.Stdout, "Warning:", log.Ldate|log.Lmicroseconds|log.Lshortfile)
	//Error = log.New(io.MultiWriter(os.Stdout,os.Stderr,file), "Error:", log.Ldate|log.Lmicroseconds|log.Lshortfile)
	Error = log.New(io.MultiWriter(os.Stderr,file), "Error:", log.Ldate|log.Lmicroseconds|log.Lshortfile)
}

func main() {

	log.Println("println,hello")
	//log.Fatalln("fatalln,hello") //打印之后跟着一个os.Exit(1)
	//log.Panicln("panic, hello") //打印之后跟着一个panic()

	fmt.Println("===no0===")

	Trace.Println("something in Trace")
	Info.Println("something in Info")
	Warning.Println("something in Warning")
	Error.Println("something in Error")

	fmt.Println("end of main")
}
