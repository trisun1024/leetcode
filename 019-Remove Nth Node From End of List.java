import java.util.*;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode first = head;
        int len = 0;
        while (first != null) {
            len++;
            first = first.next;
        }

        len -= n;
        first = temp;
        while (len > 0) {
            len--;
            first = first.next;
        }
        first.next = first.next.next;

        return temp.next;
    }

    public ListNode removeNthFromEndII(ListNode head, int n) {
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode first = temp;
        ListNode second = temp;
        for (int i = 1; i < n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;

        return temp.next;
    }
}
