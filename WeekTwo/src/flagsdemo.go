package main

import (
	"flag"
	"fmt"
)

func main() {
	verbose := flag.Bool("verbose", false, "show detailed output")
	flag.Parse()

	if *verbose {
		fmt.Println("Verbose mode is ON")

	} else {
		fmt.Println("Verbose mode is OFF")
	}
}
