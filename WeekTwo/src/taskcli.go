package main

import (
	"flag"
	"fmt"
	"os"
)

func main() {
	if len(os.Args) < 2 {
		fmt.Println("No command provided. Try:help")
		return

	}
	command := os.Args[1]
	if command == "help" {
		fmt.Println("Available commands:")
		fmt.Println("add <description>   -add a new task")
		fmt.Println("list                -show all tasks")
		fmt.Println("complete <id>       -mark a task as done")
		fmt.Println("help                - show this message")

	} else if command == "add" {
		if len(os.Args) < 3 {
			fmt.Println("Missing description. Usage:add <description>")
			return
		}
		description := os.Args[2]
		fmt.Println("Task added:", description)
	} else if command == "list" {
		verbose := flag.Bool("verbose", false, "show detailed output")
		flag.CommandLine.Parse(os.Args[2:])

		if *verbose {
			fmt.Println("Listing all tasks (verbose mode) .. (not connected to storage yet)")
		} else {
			fmt.Println("Listing all tasks... (not connected to storage yet)")
		}

	} else if command == "complete" {
		if len(os.Args) < 3 {
			fmt.Println("Missing task id. Usage:complete <id>")
			return
		}
		id := os.Args[2]
		fmt.Println("Marking task", id, "as complete... (not connected to storage yet)")
	} else {
		fmt.Println("Unknown command:", command)
		fmt.Println("Try: help")

	}
}
