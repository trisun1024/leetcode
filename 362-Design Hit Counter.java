import java.util.*;

class HitCounter {

    Deque<Integer> deque;

    /** Initialize your data structure here. */
    public HitCounter() {
        deque = new ArrayDeque<>();
    }

    /**
     * Record a hit.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        deque.offerLast(timestamp);
        while (!deque.isEmpty() && deque.peekFirst() < timestamp - 300) {
            deque.pollFirst();
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * 
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        while (!deque.isEmpty() && deque.peekFirst() <= timestamp - 300) {
            deque.pollFirst();
        }
        return deque.size();
    }
}

/**
 * Your HitCounter object will be instantiated and called as such: HitCounter
 * obj = new HitCounter(); obj.hit(timestamp); int param_2 =
 * obj.getHits(timestamp);
 */