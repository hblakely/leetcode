package scneario108

import (
	"math/rand"
	node "src/utilities/node"
)

// #########################################//
// Recursive Preorder, singular method, left-middle
// sub-slices to access portions of original nums []int
func SortedArrayToBST(nums []int) *node.TreeNode {
	if len(nums) == 0 {
		return nil
	}
	mid := len(nums) / 2
	root := &node.TreeNode{
		Val:   nums[mid],
		Left:  SortedArrayToBST(nums[:mid]),
		Right: SortedArrayToBST(nums[mid+1:]),
	}
	return root
}

//#########################################//

// #########################################//
// Recursive Preorder, global var + helper method, left-middle
var n []int

func SortedArrayToBSTLeft(nums []int) *node.TreeNode {
	n = nums
	return helperLeft(0, len(n)-1)
}

func helperLeft(left int, right int) *node.TreeNode {
	if left > right {
		return nil
	}

	// always choose left-middle node as root
	p := (left + right) / 2

	// preorder traversal: node -> left ->right
	root := &node.TreeNode{
		Val:   n[p],
		Left:  helperLeft(left, p-1),
		Right: helperLeft(p+1, right),
	}
	return root
}

//#########################################//

// #########################################//
// Recursive Preorder, global var + helper method, right middle
var n2 []int

func SortedArrayToBSTRight(nums []int) *node.TreeNode {
	n2 = nums
	return helperRight(0, len(n2)-1)
}

func helperRight(left int, right int) *node.TreeNode {
	if left > right {
		return nil
	}

	// always choose right-middle node as root
	p := (left + right) / 2
	if (left+right)%2 == 1 {
		p++
	}

	// preorder traversal: node -> left ->right
	root := &node.TreeNode{
		Val:   n2[p],
		Left:  helperRight(left, p-1),
		Right: helperRight(p+1, right),
	}
	return root
}

//#########################################//

// #########################################//
// Recursive Preorder, global var + helper function, random middle
var n3 []int

func SortedArrayToBSTRand(nums []int) *node.TreeNode {
	n3 = nums
	return helper(0, len(n)-1)
}

func helper(left int, right int) *node.TreeNode {
	if left > right {
		return nil
	}

	// always choose right-middle node as root
	p := (left + right) / 2
	if (left+right)%2 == 1 {
		p += rand.Intn(2)
	}

	// preorder traversal: node -> left ->right
	root := &node.TreeNode{
		Val:   n3[p],
		Left:  helper(left, p-1),
		Right: helper(p+1, right),
	}
	return root
}

//#########################################//
