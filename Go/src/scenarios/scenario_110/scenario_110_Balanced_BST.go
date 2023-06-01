package scenario110

import (
	"math"
	node "src/utilities/node"
)

type TreeInfo struct {
	Height   int
	Balanced bool
}

func isBalancedTreeHelper(root *node.TreeNode) TreeInfo {
	if root == nil {
		return TreeInfo{-1, true}
	}

	left := isBalancedTreeHelper(root.Left)
	if !left.Balanced {
		return TreeInfo{-1, false}
	}

	right := isBalancedTreeHelper(root.Right)
	if !right.Balanced {
		return TreeInfo{-1, false}
	}

	if math.Abs(float64(left.Height-right.Height)) < 2 {
		return TreeInfo{int(math.Max(float64(left.Height), float64(right.Height))) + 1, true}
	}
	return TreeInfo{-1, false}
}

func IsBalanced(root *node.TreeNode) bool {
	return isBalancedTreeHelper(root).Balanced
}
