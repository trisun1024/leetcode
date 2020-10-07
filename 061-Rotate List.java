import extensions.ListNode;

class RotateList {

    // Two Pointers
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        int size = 0;
        while (fast.next != null) {
            size++;
            fast = fast.next;
        }
        // move to position
        for (int j = size - n % size; j > 0; j--) {
            slow = slow.next;
        }
        // construct the list
        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;
        return dummy.next;
    }

    // Similar Idea.
    public ListNode rotateRightI(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            size++;
            cur = cur.next;
        }

        k = size - k % size;
        ListNode dummy = new ListNode(0);
        cur = head;
        ListNode prev = null;
        while (k > 0) {
            prev = cur;
            cur = cur.next;
            k--;
        }
        dummy.next = cur;
        prev.next = null;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        return dummy.next;
    }
}