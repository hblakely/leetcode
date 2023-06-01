package scenario33

import "testing"

/*
33. Search in Rotated Sorted Array

There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly rotated at an unknown
pivot index k (1 <= k < nums.length) such that the resulting array is
[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
Given the array nums after the possible rotation and an integer target,
return the index of target if it is in nums, or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.

Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 104
*/

func TestSearchRotated(t *testing.T) {
	testCases := []struct {
		nums     []int
		target   int
		expected int
	}{
		{
			nums:     []int{4, 5, 6, 7, 0, 1, 2},
			target:   0,
			expected: 4,
		},
		{
			nums:     []int{4, 5, 6, 7, 0, 1, 2},
			target:   3,
			expected: -1,
		},
		{
			nums:     []int{1},
			target:   0,
			expected: -1,
		},
	}

	for _, testCase := range testCases {
		actual := Search(testCase.nums, testCase.target)
		if actual != testCase.expected {
			t.Errorf("Expected %v but got %v for nums %v and target %v", testCase.expected, actual, testCase.nums, testCase.target)
		}
	}
}
