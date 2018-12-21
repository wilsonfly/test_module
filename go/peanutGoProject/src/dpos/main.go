package main

import (
	"strconv"
	"crypto/sha256"
	"encoding/hex"
	"time"
	"math/rand"
	"fmt"
)

//DPoS原理

//创建区块
type Block struct {
	Index     int
	PreHash   string
	HashCode  string
	BMP       int
	validator string
	TimeStamp string
}

//区块链
var Blockchain []Block

//生成block
func GenerateNextBlock(oldBlock Block, BMP int, adds string) Block {
	var newBlock Block
	newBlock.Index = oldBlock.Index + 1
	newBlock.PreHash = oldBlock.HashCode
	newBlock.BMP = BMP
	newBlock.TimeStamp = time.Now().String()
	newBlock.validator = adds
	newBlock.HashCode = GenerateHashValue(newBlock)
	return newBlock
}

//产生区块的hash
func GenerateHashValue(block Block) string {
	var hashCode = block.PreHash + block.validator + block.TimeStamp +
		strconv.Itoa(block.Index) + strconv.Itoa(block.BMP)

	var sha = sha256.New()
	sha.Write([]byte(hashCode))
	hashed := sha.Sum(nil)
	return hex.EncodeToString(hashed)
}

//存放代理人,存放delegete的地址信息
var delegate = []string{"aaa", "bbb", "ccc", "dddd"}

//随机委托人的位置
func RandDelegate() {
	rand.Seed(time.Now().Unix())
	var r = rand.Intn(3)
	t := delegate[r]
	delegate[r] = delegate[3]
	delegate[3] = t
}

func main() {
	fmt.Println(delegate)

	//创世区块
	var firstBlock Block
	firstBlock = Block{0, "NoPreHash", "NoHashCode", 0, "NoValidator", time.Now().String()}
	Blockchain = append(Blockchain, firstBlock)
	//通过n按顺序让delegate作为矿工
	var n = 0
	startNewRoundGenerateBlocksCh := make(chan bool)
	RandDelegateCh := make(chan bool)

	go func() {
	flag:
		<-startNewRoundGenerateBlocksCh
		count := 0
		for {
			//每间隔3秒产生新的区块，通过count记录睡眠次数
			count++
			time.Sleep(time.Second * 3)
			//轮到的节点进行出块
			var nextBlock = GenerateNextBlock(Blockchain[len(Blockchain)-1], 1, delegate[n])
			n++
			n = n % len(delegate)
			//firstBlock = nextBlock
			//上链
			Blockchain = append(Blockchain, nextBlock)
			fmt.Println(Blockchain)
			fmt.Println(count)
			//每30秒将代理人顺序打乱一次
			if count == 5 {
				count = 0
				RandDelegateCh <- true
				goto flag
			}
		}
	}()

	go func() {
		for {
			RandDelegate()
			fmt.Println("更换顺序后的代理人", delegate)
			startNewRoundGenerateBlocksCh <- true
			<-RandDelegateCh
		}
	}()

	time.Sleep(time.Minute * 2)
}
