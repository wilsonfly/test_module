package main

import (
	"sync"
	"fmt"
)

//下面的迭代会有什么问题？
type threadSafeSet struct {
	sync.RWMutex
	s []interface{}
}

//返回一个channel
func (set *threadSafeSet) Iter() <-chan interface{} {
	//ch := make(chan interface{}) // 解除注释看看！
	ch := make(chan interface{}, len(set.s))
	go func() {
		set.RLock()
		for k, v := range set.s {
			ch <- k
			//fmt.Println("Iter:", k, v)

			//value := v
			value := v.(string)
			//println("Iter:", k, v)
			fmt.Println("Iter:", k, value)
		}
		close(ch)
		set.RUnlock()
	}()

	return ch
}

func main() {
	th := threadSafeSet{
		s: []interface{}{"5", "6"},
	}

	v := <-th.Iter()
	s := fmt.Sprintf("%s%v", "ch", v)
	fmt.Println(v, s)


}
