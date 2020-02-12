import java.util.*;

class LFUCache {

    static class ListNode {
        int key;
        int value;
        ListNode next;
        ListNode prev;
        int count = 0;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int CAPACITY;
    private Map<Integer, ListNode> map;
    private ListNode head;
    private ListNode tail;
    private PriorityQueue<ListNode> heap;

    public LFUCache(int capacity) {
        this.CAPACITY = capacity;
        head = null;
        tail = null;
        map = new HashMap<>();
        heap = new PriorityQueue<>((a, b) -> (a.count - b.count));
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode node = map.get(key);
        node.count++;
        updateHeap(node);
        moveToHead(node);
        return node.value;
    }

    private void updateHeap(ListNode node) {
        if (node == null) {
            return;
        }
        ListNode next = node.next;
        node.prev.next = next;
        next.prev = node.prev;
    }

    private void moveToHead(ListNode node) {
        deleteNode(node);
        addToHead(node);
    }

    private void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        ListNode next = node.next;
        node.prev.next = next;
        next.prev = node.prev;
    }

    private void addToHead(ListNode node) {
        if (node == null) {
            return;
        }
        ListNode old = head.next;
        head.next = node;
        node.prev = head;
        node.next = old;
        old.prev = node;
    }

    public void put(int key, int value) {
        if (CAPACITY == 0) {
            return;
        }
        if (!map.containsKey(key) && heap.size() == CAPACITY) {
            removeLast();
        }
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.value = value;
            node.count++;
            updateHeap(node);
            moveToHead(node);
        } else {
            ListNode node = new ListNode(key, value);
            map.put(key, node);
            addToHead(node);
            heap.offer(node);
        }
    }

    private void removeLast() {
        List<ListNode> minList = new ArrayList<>();
        if (map.isEmpty() || heap.isEmpty()) {
            return;
        }
        int minCount = heap.peek().count;
        while (!heap.isEmpty() && heap.peek().count == minCount) {
            minList.add(heap.poll());
        }
        if (minList.size() == 1) {
            ListNode minNode = minList.get(0);
            deleteNode(minNode);
            map.remove(minNode.key);
        } else {
            ListNode cur = tail.prev;
            while (cur != head) {
                if (minList.contains(cur)) {
                    deleteNode(cur);
                    map.remove(cur.key);
                    for (ListNode n : minList) {
                        if (n != cur) {
                            heap.offer(n);
                        }
                    }
                    break;
                } else {
                    cur = cur.prev;
                }
            }
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such: LFUCache obj =
 * new LFUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */

class LFUCacheII {
    // use this -
    // https://leetcode.com/problems/lfu-cache/discuss/94521/JAVA-O(1)-very-easy-solution-using-3-HashMaps-and-LinkedHashSet
    // 3 HashMaps, one to store key - values, 2nd to store key frequency, 3rd to
    // store freq as key and list of keys occuring at that freq.
    // min is set at -1 to begin with.

    // class levels vars first
    private Map<Integer, Integer> vals;
    private Map<Integer, Integer> counts;
    private Map<Integer, LinkedHashSet<Integer>> freqlists; // REMEMBER Integer please.
    private final int cap;
    private int min;

    public LFUCache(int capacity) {
        cap = capacity;
        min = -1;
        vals = new HashMap<>();
        counts = new HashMap<>();
        freqlists = new HashMap<>();
        freqlists.put(1, new LinkedHashSet<>()); // REMEMBER to add freq 1 to begin with.
    }

    public int get(int key) {
        if (!vals.containsKey(key))
            return -1; // check right away, vals has all key value pairs.

        // key exits, before returning lets update its count as well as change it in the
        // freqlists from count to count+1.
        int count = counts.get(key);
        counts.put(key, count + 1);

        freqlists.get(count).remove(key); // remember freqlists has counts (1,2,3) as keys, so remove this key from
                                          // count and add it to count+1
        // additional check before tho, if this count is the min count and after
        // removing the key its list becomes zero, make the next count up as min.
        if (count == min && freqlists.get(count).size() == 0)
            min++;
        // ok now add key to the next count up please. but wait, first check if it exits
        // - create it.
        if (!freqlists.containsKey(count + 1))
            freqlists.put(count + 1, new LinkedHashSet<>());
        freqlists.get(count + 1).add(key);

        // now we can return what was really asked for :)
        return vals.get(key);
    }

    public void put(int key, int value) {
        // ok put needs capacity check first. then 2 conditions, if the key already
        // exists then update (everywhere - need to call get again).
        // if it does not exist we have to check for eviction before adding it.
        if (cap <= 0)
            return;

        if (vals.containsKey(key)) {
            vals.put(key, value); // just updates it.
            get(key); // this updates its count, its position in the freqlists etc since this was
                      // accessed.
            return; // IMPORTANT - return right away.
        }

        if (vals.size() >= cap) { // eviction time.
            // what do we remove. we use min as our key (count), gets its list from
            // freqlists and remove its first element. Remember LinkedHashSet
            // maintains order, first in first out, so the first key in the min list will be
            // the LRU from that list and is our candidate.
            int evict = freqlists.get(min).iterator().next(); // use iterator() to get the first element.
            freqlists.get(min).remove(evict); // remove from freqlists
            vals.remove(evict); // and vals
            counts.remove(evict); // and counts
        }

        // now we do what we came here for.
        vals.put(key, value);
        counts.put(key, 1); // update counts.
        min = 1; // THIS IS IMPORTANT - since this key just came in, min will automatically
                 // become 1 to reflect this, also add this key to the 1 key list
        freqlists.get(1).add(key); // add key to the 1 list.
    }
}