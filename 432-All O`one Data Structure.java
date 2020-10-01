import java.util.*;

class AllOoneDataStructure {

    class Bucket {
        int v;
        Set<String> set;
        Bucket pre;
        Bucket next;

        public Bucket(int v) {
            this.v = v;
            set = new HashSet<>();
        }
    }

    class AllOne {

        Bucket head, tail;
        Map<String, Integer> key2Vaule;
        Map<Integer, Bucket> vaule2Bucket;

        /** Initialize your data structure here. */
        public AllOne() {
            head = new Bucket(-1);
            tail = new Bucket(-1);
            head.next = tail;
            tail.pre = head;

            key2Vaule = new HashMap<>();
            vaule2Bucket = new HashMap<>();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            // en: value is the bucket that needs to be inserted zh: value 是需要插入的桶的值
            int value = key2Vaule.getOrDefault(key, 0) + 1;
            Bucket bucket = vaule2Bucket.get(value);
            if (bucket == null) {
                // value = 1 or n + 1, preBucket = head or (value2Bucket.get(n) != null)
                Bucket newBucket = insertBucketByPre(value, vaule2Bucket.get(value - 1));
                newBucket.set.add(key);
            } else {
                bucket.set.add(key);
            }
            removeKeyFromBucket(key, vaule2Bucket.get(value - 1));
            key2Vaule.put(key, value);
        }

        /**
         * Decrements an existing key by 1. If Key's value is 1, remove it from the data
         * structure.
         */
        public void dec(String key) {
            if (!key2Vaule.containsKey(key))
                return;

            // en: value is the bucket that needs to be deleted zh: value 是需要删除的桶的值
            int value = key2Vaule.get(key) - 1;
            Bucket bucket = vaule2Bucket.get(value);
            if (bucket == null && value > 0) {
                Bucket newBucket = insertBucketByNext(value, vaule2Bucket.get(value + 1));
                newBucket.set.add(key);
            } else if (value > 0) {
                bucket.set.add(key);
            }
            removeKeyFromBucket(key, vaule2Bucket.get(value + 1));
            if (value > 0)
                key2Vaule.put(key, value);
            else
                key2Vaule.remove(key);
        }

        private Bucket insertBucketByPre(int value, Bucket preBucket) {
            if (preBucket == null)
                preBucket = head;

            Bucket bucket = new Bucket(value);
            preBucket.next.pre = bucket;
            bucket.pre = preBucket;
            bucket.next = preBucket.next;
            preBucket.next = bucket;
            vaule2Bucket.put(value, bucket);

            return bucket;
        }

        private Bucket insertBucketByNext(int value, Bucket nextBucket) {
            if (nextBucket == null)
                nextBucket = tail;

            Bucket bucket = new Bucket(value);
            nextBucket.pre.next = bucket;
            bucket.next = nextBucket;
            bucket.pre = nextBucket.pre;
            nextBucket.pre = bucket;

            vaule2Bucket.put(value, bucket);
            return bucket;
        }

        private void removeKeyFromBucket(String key, Bucket bucket) {
            if (bucket == null)
                return;
            bucket.set.remove(key);
            if (bucket.set.isEmpty()) {
                // remove bucket
                bucket.pre.next = bucket.next;
                bucket.next.pre = bucket.pre;
                vaule2Bucket.remove(bucket.v);
                // help gc
                bucket = null;
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            return tail.pre == head ? "" : tail.pre.set.iterator().next();
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            return head.next == tail ? "" : head.next.set.iterator().next();
        }
    }

    /**
     * Your AllOne object will be instantiated and called as such: AllOne obj = new
     * AllOne(); obj.inc(key); obj.dec(key); String param_3 = obj.getMaxKey();
     * String param_4 = obj.getMinKey();
     */

    class AllOneI {

        class Node {
            String key;
            int value;
            Node prev;
            Node next;

            public Node(String key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private void addNewNode(Node node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            map.put(node.key, node);
        }

        private void removeNode(Node node) {
            Node prev = node.prev;
            Node next = node.next;

            node.next = null;
            node.prev = null;

            if (prev != null) {
                prev.next = next;
            }

            if (next != null) {
                next.prev = prev;
            }
        }

        private void addNode(Node prev, Node node, Node next) {
            node.next = next;
            node.prev = prev;

            if (prev != null) {
                prev.next = node;
            }

            if (next != null) {
                next.prev = node;
            }
        }

        private void deleteNode(Node node) {
            if (head.key == node.key) {
                head = node.next;
            }

            if (tail.key == node.key) {
                tail = node.prev;
            }

            removeNode(node);
            map.remove(node.key, node);
        }

        Node head;
        Node tail;
        Map<String, Node> map;

        /** Initialize your data structure here. */
        public AllOneI() {
            map = new HashMap<String, Node>();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            if (!map.containsKey(key)) {
                Node node = new Node(key, 1);
                addNewNode(node);
                return;
            }

            Node node = map.get(key);
            node.value++;

            while (node.key != head.key && node.value > node.prev.value) {
                Node prev = node.prev;
                removeNode(node);
                addNode(prev.prev, node, prev);

                if (prev.key == head.key) {
                    head = node;
                }

                if (tail.key == node.key) {
                    tail = prev;
                }
            }
        }

        /**
         * Decrements an existing key by 1. If Key's value is 1, remove it from the data
         * structure.
         */
        public void dec(String key) {
            if (!map.containsKey(key)) {
                return;
            }

            Node node = map.get(key);
            node.value--;

            if (node.value == 0) {
                deleteNode(node);
                return;
            }

            while (tail.key != node.key && node.value < node.next.value) {
                Node next = node.next;
                removeNode(node);
                addNode(next, node, next.next);

                if (next.key == tail.key) {
                    tail = node;
                }

                if (head.key == node.key) {
                    head = next;
                }
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            return head == null ? "" : head.key;
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            return tail == null ? "" : tail.key;
        }
    }

}