package main

import "fmt"

type Speaker interface {
	Speak() string
}

type Person struct {
	Name string
}

func (p Person) Speak() string {
	return " Hello, I'm " + p.Name
}

func main() {
	var s Speaker
	s = Person{"Irene"}
	fmt.Println(s.Speak())
}
