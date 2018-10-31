package main

import (
	"flag"
	"os"
	"fmt"
	"bufio"
	"strings"
	"io"
	"gx/ipfs/QmNiJuT8Ja3hMVpBHXv3Q6dwmperaQ6JjLtpMQgMCD7xvx/go-ipfs-util"
)

func main() {

	var showInfo, force bool
	flag.BoolVar(&showInfo, "v", false, "show more info")
	flag.BoolVar(&force, "f", false, "force copy file even target file exist")
	flag.Parse()

	//fmt.Println(flag.NArg(), len(flag.Args()))
	if flag.NArg() < 2 {
		flag.Usage()
		return
	}

	CopyFile(flag.Arg(0), flag.Arg(1), showInfo, force)
}

func CopyFile(src, dst string, showInfo, force bool) {
	if !force {
		//if fileExists(dst) {
		if util.FileExists(dst) {
			fmt.Printf("%s exest, override is ? y/n \n", dst)
			reader := bufio.NewReader(os.Stdin)
			content, _, _ := reader.ReadLine()
			cmd := strings.TrimSpace(string(content))
			if cmd != "y" {
				return
			}
		}
	}

	doCopyFile(src, dst)

	if showInfo {
		fmt.Printf("%s --> %s\n", src, dst)
	}
}

func fileExists(file string) bool {
	_, err := os.Stat(file)
	return err != nil || os.IsExist(err)
}

func doCopyFile(src, dst string) {
	srcFile, err := os.Open(src)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer srcFile.Close()

	dstFile, err := os.Create(dst)
	if err != nil {
		fmt.Println(err)
		return
	}
	defer dstFile.Close()

	io.Copy(dstFile, srcFile)

}
