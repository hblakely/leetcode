
public class MergeTwoSortedLinkedLists {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    /*
    You are given the heads of two sorted linked lists list1 and list2.

    Merge the two lists in a one sorted list.
    The list should be made by splicing together the nodes of the first two lists.

    Return the head of the merged linked list.
     */
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            // Start with a Dummy Node
            ListNode head = new ListNode(); // The initialized head is a placeholder
            ListNode tail = head; // Point tail to head to initialize the new list

            // Iterate til one of the two lists is empty, then append remaining nodes from the other list
            while (list1!=null && list2!=null){
                // Append the smaller of the two values to tail.next
                if(list1.val<list2.val){
                    tail.next=list1;
                    list1=list1.next;
                }else{
                    tail.next=list2;
                    list2=list2.next;
                }
                tail=tail.next; // tail is updated to be the node we just added
            }
            // Append the remaining nodes to tail (they're already sorted)
            tail.next = list1!=null?list1: list2; // if both are null, that's fine, it just signifies end of the list.

            return head.next; // head was a dummy holder, but head.next has the first real value.
        }
}
