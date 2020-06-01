import java.util.*;

/* 
* create a new node class to store the current information. Because we want to use linked list and hashmap to store the data; then the new node class should include a prev and next pointers to identify the nodes
*/
class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int k, int v) {
        this.key = k;
        this.value = v;
        prev = null;
        next = null;
    }

    void update(int k, int v) {
        this.key = k;
        this.value = v;
    }
}

class LRUCache {

    private final int CAPACITY;
    private Node head;
    private Node tail;
    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.CAPACITY = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        remove(node);
        append(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            remove(node);
        } else if (map.size() < CAPACITY) {
            node = new Node(key, value);
        } else {
            node = tail;
            remove(node);
            node.update(key, value);
        }
        append(node);
    }

    private Node remove(Node node) {
        map.remove(node.key);
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node == head) {
            head = head.next;
        }
        if (node == tail) {
            tail = tail.prev;
        }
        node.next = node.prev = null;
        return node;
    }

    private Node append(Node node) {
        map.put(node.key, node);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        return node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */