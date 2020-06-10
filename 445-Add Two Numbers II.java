class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            this.val = v;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode one = reverse(l1);
        ListNode two = reverse(l2);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int rem = 0;
        while (one != null && two != null) {
            int tot = one.val + two.val + rem;
            rem = tot / 10;
            tot = tot % 10;
            cur.next = new ListNode(tot);
            cur = cur.next;
            one = one.next;
            two = two.next;
        }
        while (one != null) {
            int tot = one.val + rem;
            rem = tot / 10;
            tot = tot % 10;
            cur.next = new ListNode(tot);
            cur = cur.next;
            one = one.next;
        }
        while (two != null) {
            int tot = two.val + rem;
            rem = tot / 10;
            tot = tot % 10;
            cur.next = new ListNode(tot);
            cur = cur.next;
            two = two.next;
        }
        if (rem != 0) {
            cur.next = new ListNode(rem);
        }
        return reverse(dummy.next);
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}