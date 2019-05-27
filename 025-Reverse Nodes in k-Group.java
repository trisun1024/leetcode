/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode cur = head;
        while (cur != null && count != k) {
            count++;
            cur = cur.next;
        }

        if (count == k) {
            cur = reverseKGroup(cur, k);
            while (count > 0) {
                count--;
                ListNode temp = head.next;
                head.next = cur;
                cur = head;
                head = temp;
            }
            head = cur;
        }
        return head;
    }
}