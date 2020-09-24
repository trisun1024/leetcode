class InsertIntoSortedCircularLinkedList {
    static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    // Two Pointers. Time = O(N);
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node n = new Node(insertVal, null);
            n.next = n;
            return n;
        }
        Node prev = head;
        Node cur = head.next;
        boolean insert = false;
        do {
            if (prev.val <= insertVal && insertVal <= cur.val) {
                // case 1. the value is in the min and max
                insert = true;
            } else if (prev.val > cur.val) {
                // case 2. the value is beyond the min or max
                if (insertVal >= prev.val || insertVal <= cur.val) {
                    insert = true;
                }
            }
            if (insert) {
                prev.next = new Node(insertVal, cur);
                return head;
            }
            prev = cur;
            cur = cur.next;
        } while (prev != head);
        // case 3. list contains uniform values
        prev.next = new Node(insertVal, cur);
        return head;
    }
}
