package main

import (
	"testing"
)

const checkMark = "\u2713"
const ballotX = "\u2717"

func TestCalc(t *testing.T) {
	t.Log("begin to test")
	sum, err := add(1, 10)
	if err != nil {
		t.Fatal(err, ballotX)
	}
	if sum == 55 {
		t.Logf("\t test result:%d %v \n", sum, checkMark)
		t.Log("\t test result:", sum, checkMark)
	}
}

func add(a, b int) (int, error) {
	var sum int
	for i := a; i <= b; i++ {
		sum += i
	}
	return sum, nil
	//return sum, errors.New("something wrong")
}
