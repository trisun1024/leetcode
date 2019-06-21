/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; next = null; } }
 */
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode x = headA;
        ListNode y = headB;
        while (x != y) {

            if (x != null) {
                x = x.next;
            } else {
                x = headB;
            }
            if (y != null) {
                y = y.next;
            } else {
                y = headA;
            }

        }
        return x;
    }
}