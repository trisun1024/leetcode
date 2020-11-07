import extensions.ListNode;

class addTwoNumbersII {

    // Reverse ListNode and reverse back.
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode one = reverse(l1);
        ListNode two = reverse(l2);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int rem = 0;
        while (one != null || two != null) {
            int v1 = one == null ? 0 : one.val;
            int v2 = two == null ? 0 : two.val;
            int tot = v1 + v2 + rem;
            rem = tot / 10;
            tot = tot % 10;
            cur.next = new ListNode(tot);
            cur = cur.next;
            if (one != null) {
                one = one.next;
            }
            if (two != null) {
                two = two.next;
            }
        }
        if (rem != 0) {
            cur.next = new ListNode(rem);
        }
        return reverse(dummy.next);
    }

    // Reverse Iteration.
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

    // Reverse Recursion.
    private ListNode reverseRec(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}