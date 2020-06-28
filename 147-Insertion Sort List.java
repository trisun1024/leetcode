class Solution {

    // Solution 1
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode preInsert, toInsert, dummyHead = new ListNode(0);
        dummyHead.next = head;

        while (head != null && head.next != null) {
            if (head.val <= head.next.val) {
                head = head.next;
            } else {
                toInsert = head.next;
                // Locate preInsert.
                preInsert = dummyHead;
                while (preInsert.next.val < toInsert.val) {
                    preInsert = preInsert.next;
                }
                head.next = toInsert.next;
                // Insert toInsert after preInsert.
                toInsert.next = preInsert.next;
                preInsert.next = toInsert;
            }
        }

        return dummyHead.next;
    }

    // Solution 2
    public ListNode insertionSortListII(ListNode head) {
        if (head == null)
            return head;
        ListNode node = head;
        while (node.next != null) {
            if (node.val > node.next.val) {
                ListNode shift = node.next;
                node.next = node.next.next;

                if (head.val > shift.val) {
                    shift.next = head;
                    head = shift;
                } else {
                    rearrange(head, shift);
                }
            } else {
                node = node.next;
            }
        }
        return head;
    }

    private void rearrange(ListNode n, ListNode shift) {
        ListNode prev = n;
        n = n.next;
        while (n != null) {
            if (n.val > shift.val) {
                shift.next = n;
                prev.next = shift;
                break;
            } else {
                prev = n;
                n = n.next;
            }
        }
    }

}