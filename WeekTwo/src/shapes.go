package main

import (
	"fmt"
	"weektwo/shapes"
)

func main() {
	shapeList := []shapes.Shape{
		shapes.Circle{Radius: 5},
		shapes.Rectangle{Width: 10, Height: 8},
	}
	for i := 0; i < len(shapeList); i++ {
		fmt.Println("Area:", shapeList[i].Area())
	}

	engine := Engine{Horsepower: 300}
	car := Car{Engine: engine}
	car.Drive()
}

type Engine struct {
	Horsepower int
}

func (e Engine) Start() {
	fmt.Println("Engine Start")

}

type Car struct {
	Engine Engine
}

func (c Car) Drive() {
	c.Engine.Start()
	fmt.Println("Car Driving..")
}
