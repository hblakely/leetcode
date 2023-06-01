/*
74. Search a 2D Matrix

You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

Example 1:

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104

---------------------------------------------------------------------------
*/

package scenarios

import (
	"testing"
)

// Running tool: /usr/local/bin/go test -timeout 30s -run ^TestSearchMatrix$ src/scenarios
func TestSearchMatrix(t *testing.T) {
	testCases := []struct {
		matrix   [][]int
		target   int
		expected bool
	}{
		{
			matrix:   [][]int{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}},
			target:   3,
			expected: true,
		},
		{
			matrix:   [][]int{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}},
			target:   13,
			expected: false,
		},
		{
			matrix:   [][]int{{-5}},
			target:   -5,
			expected: true,
		},
		{
			matrix:   [][]int{{-5}},
			target:   -10,
			expected: false,
		},
		{
			matrix:   [][]int{{-104}, {-103}},
			target:   -104,
			expected: true,
		},
		{
			matrix:   [][]int{{104}, {103}},
			target:   104,
			expected: true,
		},
		{
			matrix:   [][]int{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
			target:   5,
			expected: true,
		},
		{
			matrix:   [][]int{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
			target:   10,
			expected: false,
		},
	}
	for _, testCase := range testCases {

		actual := SearchMatrix(testCase.matrix, testCase.target)
		if actual != testCase.expected {
			t.Errorf("Expected %v but got %v for matrix %v and target %v", testCase.expected, actual, testCase.matrix, testCase.target)
		}
	}
}
