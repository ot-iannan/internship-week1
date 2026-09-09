package main

import "fmt"

// 1. The Strategy interface — the shared contract.
// Anything that wants to be a "payment method" must have Pay().
type PaymentStrategy interface {
	Pay(amountCents int) error
}

// 2. Concrete Strategies — each is one specific way to pay.

type CreditCard struct {
	Number string
}

func (c CreditCard) Pay(amount int) error {
	fmt.Println("Validating and charging card:", amount)
	return nil
}

type PayPal struct {
	Email string
}

func (p PayPal) Pay(amount int) error {
	fmt.Println("Redirecting to PayPal and confirming callback:", amount)
	return nil
}

type CryptoWallet struct {
	Address string
}

func (w CryptoWallet) Pay(amount int) error {
	fmt.Println("Waiting for wallet confirmations:", amount)
	return nil
}

// 3. The Context — needs payment done, doesn't care how.
type Checkout struct {
	Method PaymentStrategy
}

func (c Checkout) Complete(amount int) {
	c.Method.Pay(amount) // generic call — no if/else about which method
}

// 4. Usage — pick a strategy, hand it off, call the shared action.
func main() {
	checkout1 := Checkout{Method: PayPal{Email: "user@example.com"}}
	checkout1.Complete(2599)

	checkout2 := Checkout{Method: CreditCard{Number: "4242..."}}
	checkout2.Complete(4999)

	checkout3 := Checkout{Method: CryptoWallet{Address: "0xABC..."}}
	checkout3.Complete(1000)
}
