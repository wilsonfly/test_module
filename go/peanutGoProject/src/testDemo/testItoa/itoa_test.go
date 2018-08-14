package itoa_test

import (
	"testing"
	"strconv"
	"fmt"
)

func BenchmarkSprintf(b *testing.B){
	b.ResetTimer()

	for i:=0;i<b.N;i++{
		fmt.Sprintf("%d",10)
	}
}

func BenchmarkSprintf2(b *testing.B){
	var num int = 10

	b.ResetTimer()

	for i:=0;i<b.N;i++{
		fmt.Sprintf("%d",num)
	}
}

func BenchmarkFormat(b *testing.B){
	//var num int64 = 10
	num := int64(10)
	//b.Log("BenchmarkFormat",num)

	b.ResetTimer()

	for i:=0;i<b.N;i++{
		//strconv.FormatInt(int64(i),10)
		strconv.FormatInt(num,10)
	}
}

func BenchmarkItoa(b *testing.B){

	b.ResetTimer()

	for i:=0;i<b.N;i++{
		strconv.Itoa(10)
	}
}


