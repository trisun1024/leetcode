import java.util.*;

class MinStack {

    /** initialize your data structure here. */
    // this is a way to build a min stack with two extra stack tracking the minimum
    // number and how many minimum numbers.

    Deque<Integer> stack;
    Deque<Integer> min;
    Deque<Integer> count;

    public MinStack() {
        stack = new ArrayDeque<>();
        min = new ArrayDeque<>();
        count = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.offerFirst(x);
        if (min.isEmpty() || min.peekFirst() > x) {
            min.offerFirst(x);
            count.offerFirst(1);
        } else if (min.peekFirst() == x) {
            count.offerFirst(count.pollFirst() + 1);
        }
    }

    public void pop() {
        if (stack.size() == 0) {
            return;
        }
        int x = stack.pollFirst();
        if (!min.isEmpty() && x == min.peekFirst()) {
            if (count.peekFirst() == 1) {
                min.pollFirst();
                count.pollFirst();
            } else {
                count.offerFirst(count.pollFirst() - 1);
            }
        }
    }

    public int top() {
        if (stack.size() == 0) {
            return 0;
        }
        return stack.peekFirst();
    }

    public int getMin() {
        if (min.size() == 0) {
            return 0;
        }
        return min.peekFirst();
    }
}

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj =
 * new MinStack(); obj.push(x); obj.pop(); int param_3 = obj.top(); int param_4
 * = obj.getMin();
 */