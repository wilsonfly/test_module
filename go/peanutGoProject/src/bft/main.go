package main

import "fmt"

//拜占庭理论的代码实现部分

type Node struct {
	Name   string
	Status int     //1 代表去，０代表不去
	Votes  []*Node //记录账本
}

//保存４个node
var nodes = make([]*Node, 0)

func createNodes() {
	A := Node{"A", 1, make([]*Node, 0)} //去
	B := Node{"B", 1, make([]*Node, 0)} //去
	C := Node{"C", 1, make([]*Node, 0)} //去
	D := Node{"D", 0, make([]*Node, 0)} //不去
	//按照拜占庭的1/3理论，这次打仗是可行的
	nodes = append(nodes, &A)
	nodes = append(nodes, &B)
	nodes = append(nodes, &C)
	nodes = append(nodes, &D)

}

//互相转达
func votes() {
	for i := 0; i < len(nodes); i++ {
		inode := nodes[i]
		//可以将每个人的进攻状态获取出来
		fmt.Println(inode.Status)

		//将此人的状态分发给其他人,每个节点后边都跟了ABCD
		for j := 0; j < len(nodes); j++ {
			jnode := nodes[j]
			inode.Votes = append(inode.Votes, jnode)
		}

	}
}

//判断本次进攻是否可行，判断叛徒是否小于三分之一
func isValid() bool {
	result := false

	//在数组中取出最后一个对象（取出任意一个对象都行，相当于某个节点取出自己收到的数据）
	node := nodes[len(nodes)-1]
	votes := node.Votes

	cnt := 0
	for _, n := range votes {
		fmt.Println(n.Name, n.Status)
		if n.Status == 0 {
			cnt++
		}
	}

	//判断cnt只有小于n/3的情况下，才能成功,拜占庭
	if float32(cnt) < float32(len(nodes))/float32(3.0) {
		result = true
	}

	return result
}

func main() {
	createNodes()
	votes()
	fmt.Println(isValid())
}
