import java.util.*;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class CopyListWithRandomPointer {

    // Linear Scan. Time = O(N);
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> mapper = new HashMap<>();
        // use dummy node to start with
        Node dummy = new Node(0);
        Node cur = dummy;
        while (head != null) {
            if (!mapper.containsKey(head)) {
                mapper.put(head, new Node(head.val));
            }
            cur.next = mapper.get(head);
            if (head.random != null) {
                if (!mapper.containsKey(head.random)) {
                    mapper.put(head.random, new Node(head.random.val));
                }
                cur.next.random = mapper.get(head.random);
            }
            head = head.next;
            cur = cur.next;
        }
        return dummy.next;
    }

}