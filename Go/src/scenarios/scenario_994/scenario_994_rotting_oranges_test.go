package scenario994

import "testing"

func TestRottingOranges(t *testing.T) {
	testCases := []struct {
		input    [][]int
		expected int
	}{
		{
			input: [][]int{{2, 0, 1, 1, 1, 1, 1, 1, 1, 1},
				{1, 0, 1, 0, 0, 0, 0, 0, 0, 1}, {1, 0, 1, 0, 1, 1, 1, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
				{1, 0, 1, 0, 1, 0, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 0, 0, 0, 1, 0, 1}, {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
				{1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}},
			expected: 58,
		},
		{
			input:    [][]int{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}},
			expected: -1,
		},
		{
			input:    [][]int{{0, 2}},
			expected: 0,
		},
	}

	for _, testCase := range testCases {
		actual := OrangesRotting(testCase.input)
		if actual != testCase.expected {
			t.Errorf("Expected %d, got %d", testCase.expected, actual)
		}
	}
}
