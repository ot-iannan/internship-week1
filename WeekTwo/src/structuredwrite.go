package main

import (
	"fmt"
	"os"
)

func main() {
	file, err := os.Create("gotasks.txt")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	file.WriteString("1|Finish work|false\n")
	file.WriteString("2|Buy things|true\n")
	file.Close()

	fmt.Println("Done, save work")
}
