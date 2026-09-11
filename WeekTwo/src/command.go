// Creates and deletes files
package main

import "fmt"

// The Command interface — the shared contract
type Command interface {
	Execute()
	Undo()
}

// The Receiver — knows how to actually do the work
type FileSystem struct{}

func (fs FileSystem) Create(name string) { fmt.Println("Created file:", name) }
func (fs FileSystem) Delete(name string) { fmt.Println("Deleted file:", name) }

// Concrete Commands — each action, packaged as an object
type CreateFileCommand struct {
	fs   FileSystem
	name string
}

func (c CreateFileCommand) Execute() { c.fs.Create(c.name) }
func (c CreateFileCommand) Undo()    { c.fs.Delete(c.name) } // undo = opposite action

type DeleteFileCommand struct {
	fs   FileSystem
	name string
}

func (c DeleteFileCommand) Execute() { c.fs.Delete(c.name) }
func (c DeleteFileCommand) Undo()    { c.fs.Create(c.name) }

// The Invoker — runs commands AND remembers them
type CLIRunner struct {
	history []Command
}

func (r *CLIRunner) Run(cmd Command) {
	cmd.Execute()
	r.history = append(r.history, cmd) // stored for later
}

func (r *CLIRunner) UndoLast() {
	if len(r.history) == 0 {
		return
	}
	last := r.history[len(r.history)-1]
	last.Undo()
	r.history = r.history[:len(r.history)-1]
}

func main() {
	fs := FileSystem{}
	runner := &CLIRunner{}

	runner.Run(CreateFileCommand{fs: fs, name: "report.txt"})
	runner.Run(DeleteFileCommand{fs: fs, name: "old.txt"})

	runner.UndoLast() // undoes the delete — recreates old.txt
}
