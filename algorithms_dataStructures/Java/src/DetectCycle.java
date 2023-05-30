import helper.ListNode;

import java.util.HashMap;
import java.util.HashSet;

public class DetectCycle {
/*
    https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
    Given the head of a linked list, return the node where the cycle begins.
        If there is no cycle, return null.

    There is a cycle in a linked list if there is some node in the list that can be reached again
    by continuously following the next pointer. Internally, pos is used to denote the index of the
    node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle.
    Note that pos is not passed as a parameter.

    Do not modify the linked list.
 */
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> nodeHash = new HashSet<ListNode>(); // Store unique items.
        while(head != null){ // Iterate
            nodeHash.add(head);
            head=head.next;
            if(nodeHash.contains(head))
                return head; // Return if head has already been visited in the linked list.
        }
        return null; // Return null if no duplicates found.
    }


    // This solution worked but used unnecessary count, and dummy ListNode.
    public ListNode detectCycleFirstAttempt(ListNode head) { // First attempt count was unnecessary.
        ListNode cycleStarts = null;
        ListNode current = head;
        HashMap<Integer,ListNode> nodeMap = new HashMap<Integer,ListNode>();
        int count = 0;

        while(current != null){ // iterate and map to index.
            nodeMap.put(count,current);

            current=current.next;
            if(nodeMap.containsValue(current)){
                cycleStarts = current;
                break;
            }
            count++;
        }
        return cycleStarts;
    }
}
