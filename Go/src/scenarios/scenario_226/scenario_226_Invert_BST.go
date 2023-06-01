package scenario226

import (
	node "src/utilities/node"
)

// ###############################################################
// Depth First Recursion swap.
func InvertTree(root *node.TreeNode) *node.TreeNode {
	// Left becomes right, and right becomes left
	if root == nil {
		return root
	}
	temp := root.Left
	root.Left = InvertTree(root.Right)
	root.Right = InvertTree(temp)

	return root
}

// ###############################################################
// Iterative Queue Breadth First
func InvertTreeIterative(root *node.TreeNode) *node.TreeNode {
	if root == nil {
		return nil
	}

	queue := []*node.TreeNode{root}
	for len(queue) > 0 {
		current := queue[0]
		queue = queue[1:]

		temp := current.Left
		current.Left = current.Right
		current.Right = temp

		if current.Left != nil {
			queue = append(queue, current.Left)
		}
		if current.Right != nil {
			queue = append(queue, current.Right)
		}
	}

	return root
}

//###############################################################
