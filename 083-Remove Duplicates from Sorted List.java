import extensions.ListNode;

class RemoveDuplicatesFromLinkedList {

    // Iteration.
    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            if (temp.next == null)
                break;
            if (temp.val == temp.next.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    // Recursion.
    public ListNode deleteDuplicatesI(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicatesI(head.next);
        return head.val == head.next.val ? head.next : head;
    }
}
