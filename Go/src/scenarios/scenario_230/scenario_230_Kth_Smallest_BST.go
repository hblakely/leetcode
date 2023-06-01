package scenario230

import (
	utilities "src/utilities/node"
)

// Given the root of a binary search tree, and an integer k,
// return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

// #######################################################
// Iterative Traversal + Stack, stops after kth'smallest
func KthSmallest(root *utilities.TreeNode, k int) int {
	nums := []*utilities.TreeNode{}
	for {
		for root != nil { // Left until you can't left any more ;)
			nums = append(nums, root)
			root = root.Left
		}
		// root = nodeStack.pop (last node visited)
		root = nums[len(nums)-1]
		nums = nums[:len(nums)-1]

		// decrement counter
		k--

		// if k is 0, we've fount our value
		if k == 0 {
			return root.Val
		}

		// No more lefts, and not done... go right.
		root = root.Right
	}
}

//#######################################################

// #######################################################
// Recursively traverses entire tree in order, store values in array
// return value in k-1 location of array.
func KthSmallestRecursive(root *utilities.TreeNode, k int) int {
	nums := inOrder(root, make([]int, 0))
	return nums[k-1]
}

func inOrder(root *utilities.TreeNode, arr []int) []int {
	if root == nil {
		return arr
	}
	// InOrder left -> middle -> right
	arr = inOrder(root.Left, arr)  // Left until can't
	arr = append(arr, root.Val)    // Then Middle
	arr = inOrder(root.Right, arr) // Then right
	return arr
}

//#######################################################
