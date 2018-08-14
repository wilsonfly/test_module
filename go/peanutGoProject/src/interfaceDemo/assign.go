package main

import "fmt"

type Mover interface {
	Move()
}

type Locker interface {
	Lock()
	Unlock()
}

type MoverLocker interface {
	Mover
	Locker
}

type bike struct {}
func (b bike)Move(){
	fmt.Println("move the bike")
}
func (b bike)Lock(){
	fmt.Println("lock the bike")
}
func (b bike)Unlock(){
	fmt.Println("unlock the bike")
}

func main() {
	var m Mover
	var ml MoverLocker

	ml = bike{}
	m = ml
	//ml = m //Mover does not implement MoverLocker (missing Lock method)

	//b := ml.(bike)
	b := m.(bike)
	ml = b

	fmt.Println(m,ml,b)
}
