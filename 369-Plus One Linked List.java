import extensions.ListNode;

class PlusOneLinkedList {

    // One Pointer.
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode notNine = dummy;
        while (head != null) {
            if (head.val != 9) {
                notNine = head;
            }
            head = head.next;
        }
        notNine.val++;
        notNine = notNine.next;
        while (notNine != null) {
            notNine.val = 0;
            notNine = notNine.next;
        }
        return dummy.val != 0 ? dummy : dummy.next;
    }

    // Reverse & Plus.
    public ListNode plusOneI(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode rev = reverse(head);
        addOne(rev);
        return reverse(rev);
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    private void addOne(ListNode head) {
        int rem = 1;
        ListNode prev = null;
        while (head != null) {
            int sum = head.val + rem;
            rem = sum / 10;
            sum = sum % 10;
            head.val = sum;
            prev = head;
            head = head.next;
        }
        if (rem == 1) {
            prev.next = new ListNode(rem);
        }
    }

}