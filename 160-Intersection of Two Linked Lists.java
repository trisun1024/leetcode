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

    /*
     * two linked list if they share a common listnodes, then they will have a
     * intersect in middle, and after that all the nodes remain the same. Therefore,
     * we know both lists have same length from intersect node to the end node. If
     * we combine two list to two lists, one is A to B, and other is B to A. Then
     * they will meet in a intersection.
     */
}