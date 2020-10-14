import extensions.ListNode;

class SortList {

    // Merge Sort Top Down. Time = O(N*log(N)); Space = O(log(N));
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = findMiddle(head);
        ListNode right = middle.next;
        middle.next = null;
        ListNode left = head;
        ListNode newLeft = sortList(left);
        ListNode newRight = sortList(right);
        return merge(newLeft, newRight);
    }

    private ListNode findMiddle(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }
        if (a != null) {
            cur.next = a;
        } else {
            cur.next = b;
        }
        return dummy.next;
    }


    // Quick Sort.
    public ListNode sortListI(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode leftPartition = partition(head);
        if (leftPartition == null) {
            leftPartition = head;
            head = head.next;
            leftPartition.next = null;
        }
        ListNode leftSorted = sortListI(leftPartition);
        ListNode rightSorted = sortListI(head);
        ListNode leftSortedTail = findTail(leftSorted);
        leftSortedTail.next = rightSorted;
        return leftSorted;
    }

    private ListNode partition(ListNode head) {
        ListNode small = new ListNode(-1);
        ListNode big = new ListNode(-1);
        ListNode smallCur = small;
        ListNode bigCur = big;
        ListNode cur = head;
        while (cur != null) {
            if (head.val > cur.val) {
                smallCur.next = cur;
                smallCur = smallCur.next;
            } else {
                bigCur.next = cur;
                bigCur = bigCur.next;
            }
            cur = cur.next;
        }
        smallCur.next = null;
        bigCur.next = null;
        return small.next;
    }

    private ListNode findTail(ListNode head) {
        ListNode tail = head;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }
}
