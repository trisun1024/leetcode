
class RemoveDuplicatesFromLinkedListII {
    public ListNode deleteDuplicates(ListNode head) {
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
}