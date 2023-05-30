import helper.TreeNode;

import java.util.*;

public class LowestCommonAncestor {
    /*
    Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes
    p and q as the lowest node in T that has both p and q as descendants
    (where we allow a node to be a descendant of itself).”

    Constraints:
        The number of nodes in the tree is in the range [2, 105].
        -109 <= Node.val <= 109
        All Node.val are unique.
        p != q
        p and q will exist in the BST.

     Example 2:
        Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
        Output: 2
        Explanation: The LCA of nodes 2 and 4 is 2,
        since a node can be a descendant of itself according to the LCA definition.

     Example 3:
        Input: root = [2,1], p = 2, q = 1
        Output: 2
     */

    // FIRST ATTEMPT: Iterate using parent pointers. The result via leetcode.com was successful
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode parent = null;
        List<TreeNode> nodeStack = new ArrayList<>();
        nodeStack.add(root);
        Map<TreeNode,TreeNode> parentMap = new HashMap<>();
        boolean foundP = false, foundQ = false;
        parentMap.put(root,parent);

        while(!nodeStack.isEmpty()){ // Map children to parent until both q and p are found.
            parent = nodeStack.get(0);
            nodeStack.remove(parent);

            // no need to keep searching if we've already mapped
            if(parent == p){
                foundP = true;
                if(foundQ)
                    break;
            }
            if (parent == q){
                foundQ=true;
                if(foundP)
                    break;
            }

            if(parent.left!=null){
                nodeStack.add(parent.left);
                parentMap.put(parent.left,parent);
            }
            if(parent.right!=null){
                nodeStack.add(parent.right);
                parentMap.put(parent.right,parent);
            }
        }

        Set<TreeNode> pAncestors = new HashSet();
        while(p !=null){
            pAncestors.add(p);
            p = parentMap.get(p);
        }
        while(!pAncestors.contains(q))
        {
            q=parentMap.get(q);
        }
        return q;
    }

    public static void main(String[] args){
        // #####################################################
        int example1 = example1().val;
        System.out.println("Example 1");
        if(example1 != 6)
            System.out.println("FAIL! "+"Answer should be: 6");
        else {
            System.out.println("SUCCESS! ");
        }
        System.out.println("Your Answer: "+example1);
        // #####################################################
        /*int example2 = example1().val;
        System.out.println("Example 2");
        if(example1 != 6)
            System.out.println("FAIL! "+"Answer should be: 2");
        else {
            System.out.println("SUCCESS! ");
        }
        System.out.println("Your Answer: "+example2);*/
        // #####################################################

    }

    public static TreeNode example1(){
        // For basic testing verification.
        /*Example 1:
        Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
        Output: 6
        Explanation: The LCA of nodes 2 and 8 is 6.*/
        TreeNode p =  new TreeNode(2,
                new TreeNode(0),
                new TreeNode(4,
                        new TreeNode(3),
                        new TreeNode(5)
                )
        );

        TreeNode q = new TreeNode(8,
                new TreeNode(7),
                new TreeNode(9)
        );
        TreeNode root = new TreeNode(6, p, q);
        return lowestCommonAncestor(root, p, q);
    }

    public static TreeNode example2(){
        // TODO: fill out structure.
        /*
        Example 2:
        Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
        Output: 2
        Explanation: The LCA of nodes 2 and 4 is 2,
        since a node can be a descendant of itself according to the LCA definition.
         */
        TreeNode p =  new TreeNode();
        TreeNode q = new TreeNode();
        TreeNode root1 = new TreeNode();
        return lowestCommonAncestor(root1, p, q);
    }
}
