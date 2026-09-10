package shapes

import "testing"

func TestArea(t *testing.T) {
	tests := []struct {
		name     string
		shape    Shape
		expected float64
	}{
		{"circle", Circle{10}, 314.159},
		{"rectangle", Rectangle{10, 20}, 200},
		{"zero radius circle", Circle{0}, 0},
	}

	for _, test := range tests {
		result := test.shape.Area()
		if result != test.expected {
			t.Errorf(" %s: expected %v, got %v", test.name, test.expected, result)
		}
	}

}

func TestAreaEdgeCases(t *testing.T) {
	tests := []struct {
		name     string
		shape    Shape
		expected float64
	}{
		{"negative radius circle", Circle{-5}, 78.53975},
		{"zero width rectangle", Rectangle{0, 9}, 0},
	}

	for _, test := range tests {
		result := test.shape.Area()
		if result != test.expected {
			t.Errorf(" %s: expected %v, got %v", test.name, test.expected, result)
		}
	}
}
