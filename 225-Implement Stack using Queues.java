import java.util.*;

class MyStack {

    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int size = queue.size();
        while (size > 1) {
            queue.offer(queue.poll());
            size--;
        }
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        int size = queue.size();
        while (size > 1) {
            queue.offer(queue.poll());
            size--;
        }
        int val = queue.poll();
        queue.offer(val);
        return val;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such: MyStack obj =
 * new MyStack(); obj.push(x); int param_2 = obj.pop(); int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */