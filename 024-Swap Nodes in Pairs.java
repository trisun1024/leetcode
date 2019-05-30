/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
// Recursive
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode tempNext = head.next.next;
        ListNode tempFirst = head.next;
        ListNode tempSecond = head;
        head = tempFirst;
        head.next = tempSecond;
        head.next.next = swapPairs(tempNext);
        return head;
    }
}
// Iterative
/* 
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode newHead = head.next;
        while (cur != null && cur.next != null) {
            ListNode tmp = cur;
            cur = cur.next;
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
            if (cur != null && cur.next != null) tmp.next = cur.next;
        }
        return newHead;
    }
}
*/