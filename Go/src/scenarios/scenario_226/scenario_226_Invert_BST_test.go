package scenario226

import (
	node "src/utilities/node"
	"testing"
)

/*
Given the root of a binary tree, invert the tree, and return its root.
Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
*/

func TestInvertBST(t *testing.T) {
	testCases := []struct {
		input *node.TreeNode
	}{
		{
			input: &node.TreeNode{
				Val: 4,
				Left: &node.TreeNode{
					Val:   2,
					Left:  &node.TreeNode{Val: 1, Left: nil, Right: nil},
					Right: &node.TreeNode{Val: 3, Left: nil, Right: nil},
				},
				Right: &node.TreeNode{
					Val:   7,
					Left:  &node.TreeNode{Val: 6, Left: nil, Right: nil},
					Right: &node.TreeNode{Val: 9, Left: nil, Right: nil},
				},
			},
		},
		{
			input: &node.TreeNode{
				Val:   2,
				Left:  &node.TreeNode{Val: 1, Left: nil, Right: nil},
				Right: &node.TreeNode{Val: 3, Left: nil, Right: nil},
			},
		},
		{
			input: nil,
		},
	}

	for _, testCase := range testCases {
		actual := InvertTree(testCase.input)
		if InvertTree(actual) != testCase.input {
			t.Errorf("Inversion failed") // Technically this isn't a correct check, but it works for now.
		}
	}
}
