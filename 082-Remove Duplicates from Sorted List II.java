import extensions.ListNode;

class RemoveDuplicatesFromLinkedListII {

    // Iteration.
    public ListNode deleteDuplicatesI(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = dummy.next;
        while (cur != null && cur.next != null) {
            if (cur.val != cur.next.val) {
                prev = cur;
                cur = cur.next;
            } else {
                while (cur != null && cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                prev.next = cur.next;
                cur.next = null;
                cur = prev.next;
            }
        }
        return dummy.next;
    }

    // Recursion.
    public ListNode deleteDuplicates(ListNode head) {
        // base case
        if (head == null) {
            return null;
        }
        if (head.next != null && head.val == head.next.val) {
            // move to the last node with same previous
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            // return the next node which is not the same value
            return deleteDuplicates(head.next);
        }
        // re-assign next node, return head
        head.next = deleteDuplicates(head.next);
        return head;
    }
}