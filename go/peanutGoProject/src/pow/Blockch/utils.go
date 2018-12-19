package Blockch

import (
	"bytes"
	"encoding/binary"
	"log"
)

// 将int64转换为字节数组
func IntToHex(num int64) []byte {
	buff := new(bytes.Buffer)
	err := binary.Write(buff, binary.BigEndian, num)
	if err != nil {
		log.Panic(err)
	}

	//fmt.Printf("in IntetToHex, num:%d ", num)
	//fmt.Printf("in IntetToHex, return:%v \n",buff.Bytes())
	return buff.Bytes()
}
