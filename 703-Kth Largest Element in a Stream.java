import java.util.*;

class KthLargest {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private final int SIZE;

    public KthLargest(int k, int[] nums) {
        this.SIZE = k;
        for (int i : nums) {
            minHeap.offer(i);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > this.SIZE) {
            minHeap.poll();
        }
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such: KthLargest
 * obj = new KthLargest(k, nums); int param_1 = obj.add(val);
 */