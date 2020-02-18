import java.util.*;

class Solution {

    // way of flatten a list node with previous, next and child node
    public Node flatten(Node head) {
        if (head == null) {
            return head;
        }
        Node dummy = new Node(0, null, head, null);
        Node cur, prev = dummy;
        Deque<Node> stack = new ArrayDeque<>();
        stack.offerFirst(head);
        while (!stack.isEmpty()) {
            cur = stack.pollFirst();
            prev.next = cur;
            cur.prev = prev;

            if (cur.next != null) {
                stack.offerFirst(cur.next);
            }
            if (cur.child != null) {
                stack.offerFirst(cur.child);
                cur.child = null;
            }
            prev = cur;
        }
        dummy.next.prev = null;
        return dummy.next;
    }

    public ListNode flatten(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = head;
        ListNode tail = head;
        // find the tail node of the first level
        while (tail.next != null) {
            tail = tail.next;
        }
        ListNode cur = head;
        while (cur != tail) {
            // if the current node has child, change the next of the tail to this child
            // move the tail node to the end of the list of this child node
            if (cur.child != null) {
                tail.next = cur.child;
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
            cur = cur.next;
        }
        return dummy;
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    Node(int v) {
        this.val = v;
        this.prev = null;
        this.next = null;
        this.child = null;
    }

    Node(int v, Node prev, Node next, Node child) {
        this.val = v;
        this.prev = prev;
        this.next = next;
        this.child = child;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode child;

    ListNode(int v, ListNode next, ListNode child) {
        this.val = v;
        this.next = next;
        this.child = child;
    }
}