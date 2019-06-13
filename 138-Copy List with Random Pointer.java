/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        Node cur = head;
        while (cur != null) {
            Node n = new Node(cur.val);
            n.next = cur.next;
            cur.next = n;
            cur = n.next;
        }

        cur = head;
        while (cur != null) {
            cur.next.random = (cur.random != null) ? cur.random.next : null;
            cur = cur.next.next;
        }

        Node curOld = head;
        Node curNew = head.next;
        Node headOld = head.next;
        while (curOld != null) {
            curOld.next = curOld.next.next;
            curNew.next = curNew.next != null ? curNew.next.next : null;
            curOld = curOld.next;
            curNew = curNew.next;
        }

        return headOld;
    }
}