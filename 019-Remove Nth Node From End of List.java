import java.util.*;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class RemoveNthNode {
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
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode f = dummy;
        ListNode s = dummy;
        for (int i = 0; i < n+1; i++) {
            f = f.next;
        }
        while (f != null) {
            f = f.next;
            s = s.next;
        }
        s.next = s.next.next;

        return dummy.next;
    }
}
