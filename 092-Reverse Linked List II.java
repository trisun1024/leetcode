class ReverseLinkedListII {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            this.val = v;
        }
    }

    // iteration
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m > n) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // run cur node to the previous node of the node at m
        ListNode cur = dummy;
        while (m > 1) {
            cur = cur.next;
            m--;
            n--;
        }
        ListNode oldHead = cur.next;
        ListNode next = oldHead.next;
        while (m < n) {
            // store the next next node;
            ListNode nextnext = next.next;
            // update next node of next
            next.next = cur.next;
            cur.next = next;
            oldHead.next = nextnext;
            // go to next run
            next = nextnext;
            m++;
        }
        return dummy.next;
    }

    // recursion
    public ListNode reverseBetweenII(ListNode head, int m, int n) {
        if (head == null || head.next == null || m > n) {
            return head;
        }
       if(m==1) {
           return reverseNodes(head, n);
       }
       head.next = reverseBetweenII(head.next, m-1, n-1);
       return head;
    }
    
    private ListNode next;
    private ListNode reverseNodes(ListNode head, int n) {
        if(n==1) {
            next = head.next;
            return head;
        }
        ListNode tail = reverseNodes(head.next, n-1);
        head.next.next = head;
        head.next = next;
        return tail;
    }
}
