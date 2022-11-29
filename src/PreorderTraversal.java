import helper.Node;

import java.util.*;

public class PreorderTraversal {
    /*
    Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
    Nary-Tree input serialization is represented in their level order traversal.
    Each group of children is separated by the null value (See examples)
     */

    // My recursive implementation was much faster, and used marginally less memory than my iterative approach.

    //######################## RECURSIVE SOLUTION ############################ //
    public List<Integer> preorderRecursive(Node root) { // 46.4mb memory, 1ms time via leetcode.com
        List<Integer> preorder = new ArrayList<Integer>();
        if(root== null)
            return preorder;
        preorderRecursive(root,preorder);
        return preorder;
    }
    public void preorderRecursive(Node n, List<Integer> preorder){
        preorder.add(n.val);
        Iterator<Node> it = n.children.iterator();
        while(it.hasNext())
            preorderRecursive((Node)it.next(),preorder);
    }
    //######################## RECURSIVE SOLUTION ############################ //


    //######################## ITERATIVE SOLUTION ############################ //
    public List<Integer> preorderIterative(Node root) { // 47.2mb memory, 11ms time via leetcode.com
        LinkedList<Integer> preorder = new LinkedList<Integer>(); // output list
        // Invalid input, return empty List<Integer>
        if(root== null)
            return preorder;

        LinkedList<Node> nodeStack = new LinkedList<Node>(); // Keep track of nodes to traverse
        nodeStack.add(root); // Add root node to stack
        while(!nodeStack.isEmpty()){ // Loop til there are no more nodes on the stack
            Node n = nodeStack.pollLast(); // Pop last node off the stack
            preorder.add(n.val); // add value of popped node to output list
            Collections.reverse(n.children); // reverse the order of node's children to make LIFO stack
            for(Node child:n.children){
                nodeStack.add(child); // add node's children to LIFO stack
            }
        }
        return preorder;
    }
    //######################## ITERATIVE SOLUTION ############################ //
}
