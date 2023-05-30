import helper.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LevelOrder {

    /**
     * https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
     * Given the root of a binary tree, return the level order traversal of its nodes' values.
     * (i.e., from left to right, level by level).

     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     **/
    /* ################## ITERATIVE APPROACH ######################### */
    // Breadth First Iterative Approach.
    public List<List<Integer>> levelOrderIterative(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;
        List<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.add(root);
        int level=0;
        while(!nodeQueue.isEmpty()){
            levels.add(new ArrayList<Integer>()); // Add list to store values for current level.
            // ####################################################################
            // for each node on current level, append value to current level's list,
            // and add each child of current level to next level's queue
            // ####################################################################
            // set levelWidth to number of nodes at current level.
            int levelWidth = nodeQueue.size(); // must use variable; nodes.size will change when appending
            for(int i = 0; i<levelWidth; i++){ // Loop til no more nodes for this level
                TreeNode n = nodeQueue.remove(0); // pop first node from queue
                levels.get(level).add(n.val); // append node val to current level's list.
                // Append child nodes to queue for
                if(n.left!=null)
                    nodeQueue.add(n.left);
                if(n.right!=null)
                    nodeQueue.add(n.right);
            }
            level++; // increment level depth
        }
        return levels;
    }
    /* ################## ITERATIVE APPROACH ######################### */


    /* ################## RECURSIVE APPROACH ######################### */
    // Depth First preorder, top-> bottom left->right
    public List<List<Integer>> levelOrderRecursive(TreeNode root) { // 1ms time, 43.4mb memory leetcode.com
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;
        traverse(root, 0,levels); // track level via int counter
        return levels;
    }
    public void traverse(TreeNode node, int level, List<List<Integer>>levels) { // 3ms time, 43.9mb memory leetcode.com
        // add level list if size is equal to current level (bot are 0 based)
        if (levels.size() == level) levels.add(new ArrayList<Integer>());

        // add node to current level-list
        levels.get(level).add(node.val);

        // recur, adding children nodes to incremented level
        if (node.left != null) traverse(node.left, level + 1,levels);
        if (node.right != null) traverse(node.right, level + 1,levels);
    }
    /* ################## RECURSIVE APPROACH ######################### */
}
