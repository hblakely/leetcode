import helper.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class ValidateBinarySearchTree {
    /*
    https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
    Given the root of a binary tree, determine if it is a valid binary search tree (BST).

    A valid BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.


    # First thought is that each node must fall within a range of values.
    # The root node is between -infinity and +infinity, while each child node
    # falls within the range determined by its position relative to its parent.

    # The value stored in the current node must be less than its left-ancestors
    # and must be greater than its right-ancestors.

    # Each child node will have an updated range, depending on its location.
    # If the child is a left-child, that child must be less than its parent's value.
        We update the upper bound for the child's range-comparison.
    # If the child is a right-child, that child must be greater than its parent's value.
        We update the lower bound for the child's range-comparison.
    */

    /*################## ITERATIVE APPROACH ###################*/
    // This approach regularly tested to be substantially slower than the recursive approaches. \
    // 10ms time, 46mb memory leetcode.com
    // Because we're iterating through the nodes,
    // we have to keep track of the nodes we haven't processed, and their limits.
    Deque<TreeNode> nodeStack = new LinkedList<TreeNode>(); // Deque.poll() saves us lines of code.
    Deque<Integer> lowerLimit = new LinkedList<Integer>();
    Deque<Integer> upperLimit = new LinkedList<Integer>();
    public boolean isValidBST(TreeNode root){
        Integer low = null, high = null, val;
        pushToStacks(root, low, high); // Initialize stacks with root node.

        while (!nodeStack.isEmpty()){
            // Set working vars by popping items off their respective queues (eventually breaking the loop).
            TreeNode n = nodeStack.poll();
            low = lowerLimit.poll();
            high = upperLimit.poll();
            if(n==null)continue; // skip empty
            val = n.val;
            // Invalid value, return false;
            if(low!=null && val<=low)return false;
            if(high!=null && val>=high)return false;
            // Push children
            pushToStacks(n.left,low,n.val);
            pushToStacks(n.right,n.val,high);
        }
        return true; // return true as default
    }
    private void pushToStacks(TreeNode node, Integer lowerBound, Integer upperBound){
        nodeStack.add(node);
        lowerLimit.add(lowerBound);
        upperLimit.add(upperBound);
    }
    /*################## ITERATIVE APPROACH ###################*/



    /*################## RECURSIVE INORDER ###################*/
    // 1ms time, 43mb memory leetcode.com (this seems to vary between 0&1ms and anywhere between 42-44mb)
    private Integer prev;
    public boolean isValidBSTInOrder(TreeNode root){
        prev=null;  // Initialize previous node value to -infinity. We use null to safely represent that value.
        return inOrder(root);
    }
    public boolean inOrder(TreeNode node){
        if(node==null)                      return true;    // Null node returns true because no false found.
        if(!inOrder(node.left))             return false;   // Process leftmost first.
        // Because we're processing the nodes IN ORDER (values increasing left to right)
        // the current node should always be greater than the last.
        if(prev!=null && node.val<=prev)    return false;   // No more lefts, compare current to previous value.
        prev=node.val;                                      // Update prev with node's value.
        return inOrder(node.right);                         // Left, and Current processed, now process right.
    }
    /*################## RECURSIVE INORDER ###################*/


    /*################## REFINED PREORDER ###################*/
    // 0ms time, 42.2mb memory leetcode.com (this seems to vary between 0&1ms and anywhere between 42-44mb)
    public boolean isValidBSTRefinedPreorder(TreeNode root) {   // This is my preferred approach.
        return validate(root,null,null);            // Begin with root node and infinite range.
    }
    private boolean validate(TreeNode node,Integer lower, Integer upper){
        if(node==null)  return true;                            // No false found, must be true.
        if(lower != null && node.val <= lower)  return false;   // node.val out of bounds: terminate with false.
        if(upper != null && node.val >= upper)  return false;   // node.val out of bounds: terminate with false.
        // Update upper bound for left node. Update lower bound for right node. Both child nodes must be valid.
        return validate(node.left,lower,node.val) && validate(node.right,node.val,upper);
    }
    /*################## REFINED PREORDER ###################*/


    /*################## FIRST ATTEMPT: Recursive Preorder ###################*/
    // Recursion seems to be the simplest way to write this solution, so I'll start here.
    // After having submitted this solution multiple times, we've various results for speed and memory usage.
    // The last submission via leetcode.com gave us 0ms time (100%), and 41.6mb usage (95.24%).
    // These results seem to fluctuate without reason between 0-1ms, and 41.6-44.5mb. I'm curious about that.
    boolean isBST = true;                       // Assume root is a Binary Search Tree until proven otherwise.
    public boolean isValidBSTRecursiveFirstAttempt(TreeNode root) {
        recur(root, new Integer[]{null, null}); // Begin with root node and infinite range.
        return isBST;
    }
    private void recur(TreeNode node, Integer[] range){
        boolean inRange = true;     // Assume node.val is within range until proven otherwise.
        if(range[0]!=null)          inRange = node.val>range[0]; // current value must be greater than lower bound.
        if(range[1]!=null&&inRange) inRange = node.val<range[1]; // current value must be less than upper bound.
        // Set inRange for upper bound only if inRange is still true after comparing node.val to lower bound
        // to prevent overwriting a false value.

        if(!inRange){               // If node.val isn't within acceptable range, this isn't a Binary Search Tree.
            isBST=false;
            return;
        }

        // For each child, recur with updated range.
        TreeNode leftChild  = node.left;
        TreeNode rightChild = node.right;
        if(leftChild!=null)     recur(leftChild,new Integer[]{range[0],node.val});     // Update upper bound for left.
        if(rightChild!=null)    recur(rightChild,new Integer[]{node.val,range[1]});    // Update lower bound for right.
    }
    /*################## FIRST ATTEMPT: Recursive Preorder ###################*/
}