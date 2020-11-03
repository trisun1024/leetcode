import extensions.ListNode;

class InsertionSortList {

    // Dummy Head. 
    public ListNode insertionSortList(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0), preInsert = null, toInsert = null;
        dummy.next = head;
        while (head != null && head.next != null) {
            // if value in order then skip
            if (head.val <= head.next.val) {
                head = head.next;
            } else {
                toInsert = head.next;
                preInsert = dummy;
                // locate the position to insert
                while (preInsert.next.val < toInsert.val) {
                    preInsert = preInsert.next;
                }
                // store the next
                head.next = toInsert.next;
                // insert
                toInsert.next = preInsert.next;
                preInsert.next = toInsert;
            }
        }
        return dummy.next;
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