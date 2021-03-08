
class MyHashMap {

    private ListNode[] nodes;
    private int CAPACITY;

    /** Initialize your data structure here. */
    public MyHashMap() {
        this.CAPACITY = 10000;
        this.nodes = new ListNode[CAPACITY];
    }

    public MyHashMap(int n) {
        this.CAPACITY = n;
        this.nodes = new ListNode[CAPACITY];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = getIndex(key);
        if (nodes[index] == null) {
            nodes[index] = new ListNode(-1, -1);
        }
        ListNode prev = find(nodes[index], key);
        if (prev.next == null) {
            prev.next = new ListNode(key, value);
        } else {
            prev.next.val = value;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map
     * contains no mapping for the key
     */
    public int get(int key) {
        int index = getIndex(key);
        if (nodes[index] == null) {
            return -1;
        }
        ListNode node = find(nodes[index], key);
        return node.next == null ? -1 : node.next.val;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping
     * for the key
     */
    public void remove(int key) {
        int index = getIndex(key);
        if (nodes[index] == null) {
            return;
        }
        ListNode prev = find(nodes[index], key);
        if (prev.next == null) {
            return;
        }
        prev.next = prev.next.next;
    }

    private int getIndex(int key) {
        return Integer.hashCode(key) % nodes.length;
    }

    private ListNode find(ListNode node, int key) {
        ListNode cur = node;
        ListNode prev = null;
        while (cur != null && cur.key != key) {
            prev = cur;
            cur = cur.next;
        }
        return prev;
    }
}

class ListNode {
    int key;
    int val;
    ListNode next;

    ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}