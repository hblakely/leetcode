package scenario230

import (
	bst "src/scenarios/scenario_701"
	node "src/utilities/node"
	"testing"
)

/*
Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104

Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
*/

func TestKthSmallest(t *testing.T) {
	testCases := []struct {
		root     *node.TreeNode
		k        int
		expected int
	}{
		{
			root: &node.TreeNode{
				Val: 3,
				Left: &node.TreeNode{
					Val: 1, Right: &node.TreeNode{Val: 2},
				},
				Right: &node.TreeNode{Val: 4},
			},
			k:        1,
			expected: 1,
		},
		{
			root:     bst.MakeBST([]int{5, 3, 6, 2, 4, 1}),
			k:        3,
			expected: 3,
		},
	}
	for _, testCase := range testCases {
		actual := KthSmallest(testCase.root, testCase.k)
		if actual != testCase.expected {
			t.Errorf("Expected %d, got %d", testCase.expected, actual)
		}
	}
}

/*
Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:


Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3
*/
