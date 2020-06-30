/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        while (cur != null) {
            if (cur.val == val) {
                ListNode next = cur.next;
                cur.next = null;
                prev.next = next;
                cur = prev;
            }
            prev = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}