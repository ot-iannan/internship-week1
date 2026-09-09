package main

import "fmt"

// The contract: any payment method must be able to Pay()
type PaymentStrategy interface {
	Pay(amount int)
}

// Each payment method does the same job, differently
type CreditCard struct{}

func (c CreditCard) Pay(amount int) {
	fmt.Println("Paid $", amount, "using Credit Card")
}

type PayPal struct{}

func (p PayPal) Pay(amount int) {
	fmt.Println("Paid $", amount, "using PayPal")
}

type CryptoWallet struct{}

func (w CryptoWallet) Pay(amount int) {
	fmt.Println("Paid $", amount, "using Crypto")
}

// The Context: just knows "something" can Pay()
type Checkout struct {
	Method PaymentStrategy
}

func (c Checkout) Complete(amount int) {
	c.Method.Pay(amount)
}

func main() {
	Checkout{Method: PayPal{}}.Complete(25)
	Checkout{Method: CreditCard{}}.Complete(50)
	Checkout{Method: CryptoWallet{}}.Complete(10)
}
