import helper.ListNode;
/*
Given the head of a singly linked list, return the middle node of the linked list.
If there are two middle nodes, return the second middle node.
 */
public class MiddleNode {
    public ListNode middleNode(ListNode head) {
        int count = 0; // track number of nodes
        ListNode current = head;
        while (current != null){ // iterate to count nodes
            count++;
            current = current.next;
        }
        // once the count is established we can divide count by 2 to get the middle index.
        for (int i =1; i<=count/2; i++){ // iterate from first to middle
            head = head.next;
        }
        return head;
    }
}
