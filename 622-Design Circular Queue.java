
class DesignCircularQueue {

    // Array.
    class MyCircularQueue {

        private int[] queue;
        private int headIndex;
        private int count;
        private int capacity;

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            capacity = k;
            queue = new int[k];
            headIndex = 0;
            count = 0;
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is
         * successful.
         */
        public boolean enQueue(int value) {
            if (count == capacity) {
                return false;
            }
            queue[(headIndex + count) % capacity] = value;
            count++;
            return true;
        }

        /**
         * Delete an element from the circular queue. Return true if the operation is
         * successful.
         */
        public boolean deQueue() {
            if (count == 0) {
                return false;
            }
            headIndex = (headIndex + 1) % capacity;
            count -= 1;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if (count == 0) {
                return -1;
            }
            return queue[headIndex];
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if (count == 0) {
                return -1;
            }
            int tail = (headIndex + count - 1) % capacity;
            return queue[tail];
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return count == 0;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return count == capacity;
        }
    }

    // Array with Thread-Safe
    class ReentrantLock {
        ReentrantLock() {

        }

        void lock() {

        }

        void unlock() {

        }
    }

    class MyCircularQueueI {

        private Node head, tail;
        private int count;
        private int capacity;
        // Additional variable to secure the access of our queue
        private ReentrantLock queueLock = new ReentrantLock();

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueueI(int k) {
            this.capacity = k;
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is
         * successful.
         */
        public boolean enQueue(int value) {
            // ensure the exclusive access for the following block.
            queueLock.lock();
            try {
                if (this.count == this.capacity)
                    return false;

                Node newNode = new Node(value);
                if (this.count == 0) {
                    head = tail = newNode;
                } else {
                    tail.nextNode = newNode;
                    tail = newNode;
                }
                this.count += 1;

            } finally {
                queueLock.unlock();
            }
            return true;
        }
    }

    // LinkedList
    class Node {
        public int value;
        public Node nextNode;

        public Node(int value) {
            this.value = value;
            this.nextNode = null;
        }
    }

    class MyCircularQueueII {

        private Node head, tail;
        private int count;
        private int capacity;

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueueII(int k) {
            this.capacity = k;
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is
         * successful.
         */
        public boolean enQueue(int value) {
            if (this.count == this.capacity)
                return false;

            Node newNode = new Node(value);
            if (this.count == 0) {
                head = tail = newNode;
            } else {
                tail.nextNode = newNode;
                tail = newNode;
            }
            this.count += 1;
            return true;
        }

        /**
         * Delete an element from the circular queue. Return true if the operation is
         * successful.
         */
        public boolean deQueue() {
            if (this.count == 0)
                return false;
            this.head = this.head.nextNode;
            this.count -= 1;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if (this.count == 0)
                return -1;
            else
                return this.head.value;
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if (this.count == 0)
                return -1;
            else
                return this.tail.value;
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return (this.count == 0);
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return (this.count == this.capacity);
        }
    }
}