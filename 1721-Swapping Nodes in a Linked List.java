import extensions.ListNode;

class SwappingNodesInLinkedList {

    // Two Passes. Time = O(N^2);
    public ListNode swapNodesI(ListNode head, int k) {
        int len = 0;
        ListNode front = null;
        ListNode end = null;
        ListNode cur = head;
        // find front node
        while (cur != null) {
            len++;
            if (len == k) {
                front = cur;
            }
            cur = cur.next;
        }
        // loop the end node
        end = head;
        for (int i = 1; i <= len - k; i++) {
            end = end.next;
        }
        // swap nodes
        int temp = front.val;
        front.val = end.val;
        end.val = temp;
        return head;
    }

    // Single Pass.
    public ListNode swapNodes(ListNode head, int k) {
        int len = 0;
        ListNode front = null;
        ListNode end = null;
        ListNode cur = head;
        // find front node
        while (cur != null) {
            len++;
            if (end != null) {
                end = end.next;
            }
            if (len == k) {
                front = cur;
                end = head;
            }
            cur = cur.next;
        }
        // swap nodes
        int temp = front.val;
        front.val = end.val;
        end.val = temp;
        return head;
    }
}