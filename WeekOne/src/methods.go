package main

import "fmt"

type Person struct {
	Name string
	Age  int
}

func (p Person) Greet() {
	fmt.Println("I am", p.Name)
}

func main() {
	p := Person{Name: "Irene", Age: 22}
	p.Greet()
}
