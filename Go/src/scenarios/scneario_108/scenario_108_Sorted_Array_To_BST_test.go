package scneario108

import (
	balance "src/scenarios/scenario_110"
	"testing"
)

/*
Given an integer array nums where the elements are sorted in ascending order,
convert it to a height-balanced binary search tree.

Constraints:
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.
*/

func TestSortedArrayToBST(t *testing.T) {
	testCases := []struct {
		input    []int
		expected bool
	}{
		{
			input:    []int{-10, -3, 0, 5, 9},
			expected: true,
		},
		{
			input:    []int{1, 3},
			expected: true,
		},
	}

	for _, testCase := range testCases {
		tree := SortedArrayToBST(testCase.input)
		// validate output of test, Use Scenario 110.
		if balance.IsBalanced(tree) != testCase.expected {
			t.Errorf("tree is not balanced")
		}
	}
}
