package main

import (
	"errors"
	"fmt"
)

func celsiusToFahrenheit(c float64) float64 {
	return c*9/5 + 32
}

func fahrenheitToCelsius(f float64) float64 {
	return (f - 32) * 5 / 9
}

func validateCelsius(c float64) error {
	if c < -273.15 {
		return errors.New("Celcius must be greater than -273.15")

	}
	return nil
}

func main() {
	celsius := 25

	err := validateCelsius(celsius)

	if err != nil {
		fmt.Println("Error and stop:", err)
		return
	}

	fahrenheit := celsiusToFahrenheit(celsius)
	fmt.Println(celsius, "C is", fahrenheit, "F")

	invalidCelsius := -300.0
	err = validateCelsius(invalidCelsius)
	if err != nil {
		fmt.Println("Error:", err)
	}
}
