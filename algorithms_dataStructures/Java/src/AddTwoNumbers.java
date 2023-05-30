import helper.ListNode;

public class AddTwoNumbers {
    /*
    You are given two non-empty linked lists representing two non-negative integers.
    The digits are stored in reverse order, and each of their nodes contains a single digit.
    Add the two numbers and return the sum as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.


    Follow up

    What if the the digits in the linked list are stored in non-reversed order? For example:

    (3→4→2)+(4→6→5)=8→0→7(3 \to 4 \to 2) + (4 \to 6 \to 5) = 8 \to 0 \to 7(3→4→2)+(4→6→5)=8→0→7

    ANSWER TO FOLLOW UP: Same as normal, but reverse the lists first, and then reverse the answer.
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode start = null, current = null, next = null;
        int carry = 0;

        while(l1!=null || l2!=null){ // Loop through digits, combine, minus carry
            int val = (l1!=null?l1.val:0) + (l2!=null?l2.val:0) + carry;
            if(val>=10){
                val = val-10;
                carry = 1;
            }else{carry=0;}
            next = new ListNode(val);
            if(start==null){
                start = next;
                current = start;
            }else{
                current.next=next;
                current = next;
            }
            if(l1!=null)
                l1=l1.next;
            if(l2!=null)
                l2=l2.next;
        }

        if(carry>0){  // Add whatever carries over.
            next = new ListNode(carry);
            current.next=next;
        }
        return start;
    }

    public static void main(String[] args){
        example1();
        example2();
        example3();
    }

    /**
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     */
    private static void example1(){
        System.out.println("example1");
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode result = addTwoNumbers(l1,l2);
        System.out.print("l1:\t\t");
        while (l1 != null) {
            System.out.print(l1.val + " ");
            l1=l1.next;
        }
        System.out.print("\nl2:\t\t");
        while (l2 != null) {
            System.out.print(l2.val + " ");
            l2=l2.next;
        }
        System.out.print("\nresult:\t");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println("\n");
    }

    /**
     * Input: l1 = [0], l2 = [0]
     * Output: [0]
     */
    private static void example2(){
        System.out.println("example2");
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode result = addTwoNumbers(l1,l2);
        System.out.print("l1:\t\t");
        while (l1 != null) {
            System.out.print(l1.val + " ");
            l1=l1.next;
        }
        System.out.print("\nl2:\t\t");
        while (l2 != null) {
            System.out.print(l2.val + " ");
            l2=l2.next;
        }
        System.out.print("\nresult:\t");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println("\n");
    }

    /**
     * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * Output: [8,9,9,9,0,0,0,1]
     */
    private static void example3(){
        System.out.println("example3");
        ListNode l1 = new ListNode(9, new ListNode(9,new ListNode(9,
                new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9)))))));
        ListNode l2 = new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9))));
        ListNode result = addTwoNumbers(l1,l2);
        System.out.print("l1:\t\t");
        while (l1 != null) {
            System.out.print(l1.val + " ");
            l1=l1.next;
        }
        System.out.print("\nl2:\t\t");
        while (l2 != null) {
            System.out.print(l2.val + " ");
            l2=l2.next;
        }
        System.out.print("\nresult:\t");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println("\n");
    }
}
