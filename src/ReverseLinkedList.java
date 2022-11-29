import helper.ListNode;
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode previous = null; // null terminates the newly reversed linked list

        while(head!=null){ // iterate until terminated null of original list
            ListNode next = head.next; // placeholder for iterating through head
            head.next = previous; // overwrite head.next with previous to reverse order of link
            previous = head; // set previous to current
            head = next; // move to next value in original list
        }
        return previous;
    }
}
