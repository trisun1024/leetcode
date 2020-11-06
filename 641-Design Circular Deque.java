class MyCircularDeque {

    private int[] arr;
    private int front;
    private int end;
    private int size;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        arr = new int[k];
        size = k;
        front = -1;
        end = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is
     * successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        if (front == -1) {
            front = 0;
            end = 0;
        } else if (front == 0) {
            front = size - 1;
        } else {
            front = front - 1;
        }
        arr[front] = value;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is
     * successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        if (front == -1) {
            front = 0;
            end = 0;
        } else if (end == size - 1) {
            end = 0;
        } else {
            end = end + 1;
        }
        arr[end] = value;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is
     * successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        if (front == end) {
            front = -1;
            end = -1;
        } else {
            if (front == size - 1) {
                front = 0;
            } else {
                front = front + 1;
            }
        }
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is
     * successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        if (front == end) {
            front = -1;
            end = -1;
        } else if (end == 0) {
            end = size - 1;
        } else {
            end = end - 1;
        }
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return arr[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty() || end < 0) {
            return -1;
        }
        return arr[end];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return front == -1;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return ((front == 0 && end == size - 1) || front == end + 1);
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k); boolean param_1 =
 * obj.insertFront(value); boolean param_2 = obj.insertLast(value); boolean
 * param_3 = obj.deleteFront(); boolean param_4 = obj.deleteLast(); int param_5
 * = obj.getFront(); int param_6 = obj.getRear(); boolean param_7 =
 * obj.isEmpty(); boolean param_8 = obj.isFull();
 */