import java.util.*;

class FreqStack {

    Map<Integer, Integer> freq;
    Map<Integer, Deque<Integer>> group;
    int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int x) {
        int f = freq.getOrDefault(x, 0) + 1;
        freq.put(x, f);
        if (f > maxFreq) {
            maxFreq = f;
        }
        Deque<Integer> stack;
        if (!group.containsKey(f)) {
            stack = new LinkedList<>();
            stack.offerFirst(x);
            group.put(f, stack);
        } else {
            stack = group.get(f);
            stack.offerFirst(x);
        }
    }

    public int pop() {
        int x = group.get(maxFreq).pollFirst();
        freq.put(x, freq.get(x) - 1);
        if (group.get(maxFreq).size() == 0) {
            maxFreq--;
        }
        return x;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such: FreqStack obj
 * = new FreqStack(); obj.push(x); int param_2 = obj.pop();
 */