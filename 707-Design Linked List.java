class MyLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            this.val = v;
        }
    }

    private int size;
    private ListNode head;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.size = 0;
        this.head = new ListNode(0);
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is
     * invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = head;
        while (cur != null && index > 0) {
            cur = cur.next;
            index--;
        }
        return cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After
     * the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index
     * equals to the length of linked list, the node will be appended to the end of
     * linked list. If index is greater than the length, the node will not be
     * inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        ++size;
        ListNode cur = head;
        while (cur != null && index > 0) {
            cur = cur.next;
            index--;
        }
        ListNode node = new ListNode(val);
        node.next = cur.next;
        cur.next = node;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        ListNode cur = head;
        while (cur != null && index > 0) {
            cur = cur.next;
            index--;
        }
        cur.next = cur.next.next;
    }
}

class MyLinkedListII {

    static class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode(int x) {
            val = x;
        }
    }

    int size;
    // sentinel nodes as pseudo-head and pseudo-tail
    ListNode head, tail;

    public MyLinkedListII() {
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is
     * invalid, return -1.
     */
    public int get(int index) {
        // if index is invalid
        if (index < 0 || index >= size)
            return -1;

        // choose the fastest way: to move from the head
        // or to move from the tail
        ListNode curr = head;
        if (index + 1 < size - index)
            for (int i = 0; i < index + 1; ++i)
                curr = curr.next;
        else {
            curr = tail;
            for (int i = 0; i < size - index; ++i)
                curr = curr.prev;
        }

        return curr.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After
     * the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode pred = head, succ = head.next;

        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode succ = tail, pred = tail.prev;

        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index
     * equals to the length of linked list, the node will be appended to the end of
     * linked list. If index is greater than the length, the node will not be
     * inserted.
     */
    public void addAtIndex(int index, int val) {
        // If index is greater than the length,
        // the node will not be inserted.
        if (index > size)
            return;

        // [so weird] If index is negative,
        // the node will be inserted at the head of the list.
        if (index < 0)
            index = 0;

        // find predecessor and successor of the node to be added
        ListNode pred, succ;
        if (index < size - index) {
            pred = head;
            for (int i = 0; i < index; ++i)
                pred = pred.next;
            succ = pred.next;
        } else {
            succ = tail;
            for (int i = 0; i < size - index; ++i)
                succ = succ.prev;
            pred = succ.prev;
        }

        // insertion itself
        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        // if the index is invalid, do nothing
        if (index < 0 || index >= size)
            return;

        // find predecessor and successor of the node to be deleted
        ListNode pred, succ;
        if (index < size - index) {
            pred = head;
            for (int i = 0; i < index; ++i)
                pred = pred.next;
            succ = pred.next.next;
        } else {
            succ = tail;
            for (int i = 0; i < size - index - 1; ++i)
                succ = succ.prev;
            pred = succ.prev.prev;
        }

        // delete pred.next
        --size;
        pred.next = succ;
        succ.prev = pred;
    }
}