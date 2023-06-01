package scenario701

import node "src/utilities/node"

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func InsertIntoBST(root *node.TreeNode, val int) *node.TreeNode {
	if root == nil {
		return &node.TreeNode{Val: val, Left: nil, Right: nil}
	}
	// Insert left
	if val < root.Val {
		root.Left = InsertIntoBST(root.Left, val)
	} else if val > root.Val {
		root.Right = InsertIntoBST(root.Right, val)
	} //else {
	// Do nothing if root.Val == val
	// Node exists ;)
	//}
	return root
}

func MakeBST(nums []int) *node.TreeNode {
	var root *node.TreeNode
	for _, num := range nums {
		root = InsertIntoBST(root, num)
	}
	return root
}
