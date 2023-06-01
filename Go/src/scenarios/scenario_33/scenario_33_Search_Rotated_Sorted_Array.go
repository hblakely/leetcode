package scenario33

// ###################### Binary Search One Pass ############################//
func Search(nums []int, target int) int {
	start := 0
	end := len(nums) - 1
	for start <= end {
		mid := start + (end-start)/2
		if nums[mid] == target {
			return mid
		} else if nums[mid] >= nums[start] {
			if target >= nums[start] && target < nums[mid] {
				end = mid - 1
			} else {
				start = mid + 1
			}
		} else {
			if target <= nums[end] && target > nums[mid] {
				start = mid + 1
			} else {
				end = mid - 1
			}
		}
	}
	return -1
}

//################### END Binary Search One Pass ###########################//

//############### Find Rotation + Binary Search 2 Pass ####################//
/*
	1) find pivot index
	2) set lo and hi of binary search depending on pivot index values
	3) binary search with appropriately initialized lo/hi values
*/
func Search_Find_Rotation(nums []int, target int) int {
	n := len(nums)

	if n == 1 {
		if nums[0] == target {
			return 0
		}
		return -1
	}

	rotateIndex := findRotateIndex(0, n-1, nums)

	// if target is the smallest element
	if nums[rotateIndex] == target {
		return rotateIndex
	}
	// if array is not rotated, search in the entire array
	if rotateIndex == 0 {
		return search(0, n-1, nums, target)
	}
	if target < nums[0] {
		// search in the right side
		return search(rotateIndex, n-1, nums, target)
	}
	// search in the left side
	return search(0, rotateIndex, nums, target)
}

func findRotateIndex(left, right int, nums []int) int {
	if nums[left] < nums[right] {
		return 0
	}

	for left <= right {
		pivot := (left + right) / 2
		if nums[pivot] > nums[pivot+1] {
			return pivot + 1
		} else {
			if nums[pivot] < nums[left] {
				right = pivot - 1
			} else {
				left = pivot + 1
			}
		}
	}
	return 0
}

// Binary Search
func search(left, right int, nums []int, target int) int {
	for left <= right {
		pivot := (left + right) / 2
		if nums[pivot] == target {
			return pivot
		} else {
			if target < nums[pivot] {
				right = pivot - 1
			} else {
				left = pivot + 1
			}
		}
	}
	return -1
}

//################# END Find Rotation + Binary Search 2 Pass ####################//

//######################## FIRST ATTEMPT ################################//
/**
	First Thoughts:
	O(log n) search of a (possibly) split/rotated array of ints
	Traditional binary search [O(log n)] doesn't work with the rotation.
	How to update the algorithm to handle the rotation?

	This implementation is a bit tedious to read, but basically we determine if there's a split,
	Then we initialize the binary search as normal, but alter how we update hi and lo depending
	on the presence of a split.

	Since our input is sorted, and then rotated, we can check to see if first and last values
	are in ascending order, and if they're not we have a split.
**/
func SearchFirstAttempt(nums []int, target int) int {

	hi := len(nums) - 1
	lo := 0

	// Determine if split exists
	split := false
	first := nums[0]
	last := nums[len(nums)-1]
	if first > last {
		split = true
		if target < first && target > last {
			return -1
		} // not in list
	}

	for hi >= lo {
		mid := (hi + lo) / 2
		val := nums[mid]
		if val == target {
			return mid
		}
		if val > target {
			if split {
				if target < first && val > last {
					lo = mid + 1
				} else {
					hi = mid - 1
				}
			} else {
				hi = mid - 1
			}
		} else { // val < target
			if split {
				if target > last && val < first {
					hi = mid - 1
				} else {
					lo = mid + 1
				}
			} else {
				lo = mid + 1
			}
		}
	}
	return -1
}

//###################### END FIRST ATTEMPT ################################//
