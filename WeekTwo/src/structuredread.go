package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	file, err := os.Open("gotasks.txt")
	if err != nil {
		fmt.Println("Could not read tasks file:", err)
		return
	}
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		parts := strings.Split(line, "|")

		if len(parts) == 2 {
			fmt.Println(line)
		} else {
			fmt.Println("Skipping bad line:", line)
		}
	}
	file.Close()
}
