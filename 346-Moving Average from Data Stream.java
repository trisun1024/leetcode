import java.util.*;

class MovingAverage {

    private final int SIZE;
    private int head;
    private int windowSum;
    private int count;
    private int[] queue;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.SIZE = size;
        queue = new int[SIZE];
        head = 0;
        windowSum = 0;
        count = 0;
    }

    public double next(int val) {
        ++count;
        int tail = (head + 1) % SIZE;
        windowSum = windowSum - queue[tail] + val;
        head = (head + 1) % SIZE;
        queue[head] = val;
        return windowSum * 1.0 / Math.min(SIZE, count);
    }
}

class MovingAverageII {
    int size, windowSum = 0, count = 0;
    Deque<Integer> queue = new ArrayDeque<Integer>();

    public MovingAverageII(int size) {
        this.size = size;
    }

    public double next(int val) {
        ++count;
        // calculate the new sum by shifting the window
        queue.add(val);
        int tail = count > size ? (int) queue.poll() : 0;

        windowSum = windowSum - tail + val;

        return windowSum * 1.0 / Math.min(size, count);
    }
}
