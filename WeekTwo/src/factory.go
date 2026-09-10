// Sample code behind a download Report button
package main

import "fmt"

// The Product interface — the shared shape
type Exporter interface {
	Export(data string) string
}

// Concrete Products — the actual things that get built
type PDFExporter struct{}

func (p PDFExporter) Export(data string) string {
	return "PDF file containing: " + data
}

type CSVExporter struct{}

func (c CSVExporter) Export(data string) string {
	return "CSV rows containing: " + data
}

type JSONExporter struct{}

func (j JSONExporter) Export(data string) string {
	return `{"Data:": "` + data + `"}`
}

// The Factory Method — the ONE place that knows how to build each kind
func NewExporter(kind string) Exporter {
	switch kind {
	case "pdf":
		return PDFExporter{}
	case "csv":
		return CSVExporter{}

	case "json":
		return JSONExporter{}
	default:
		return CSVExporter{} // sensible default
	}
}

func main() {
	exporter := NewExporter("pdf")
	fmt.Println(exporter.Export("sales report"))
}
