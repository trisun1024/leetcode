class Solution {
    public void reorderList(ListNode head) {
        if(head==null) {
            return;
        }
        // find the middle 
        ListNode s = head, f = head;
        while(f != null && f.next!=null) {
            s = s.next;
            f = f.next.next;
        }
        // reverse the mid point
        ListNode prev = null, cur = s;
        while(cur!=null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        // merge two list
        ListNode first = head, second = prev;
        while(second.next != null) {
            ListNode next = first.next;
            first.next = second;
            first = next;
            next = second.next;
            second.next = first;
            second = next;
        }
    }

    // Separate functions
    public void reorderListII(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // step 1: find the mid
        ListNode mid = findMid(head);
        ListNode secondHalf = mid.next;
        mid.next = null;
        // step 2: reverse the second half
        // step 3: merge the two halves
        merge(head, reverse(secondHalf));
    }

    private ListNode findMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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

    private void merge(ListNode one, ListNode two) {
        ListNode cur = one;
        while (two != null) {
            ListNode tmp = cur.next;
            cur.next = two;
            two = two.next;
            cur.next.next = tmp;
            cur = tmp;
        }
    }
}