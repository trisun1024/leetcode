import java.util.*;

class DailyTemperatures {

    // Stack.
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        // stack store the index of past temperature
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i =n-1; i >= 0; i--) {
            // current temperature is higher or equal to stack top, then poll
            while (!stack.isEmpty() && T[i] >= T[stack.peekFirst()]) {
                stack.pollFirst();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peekFirst() - i;
            stack.offerFirst(i);
        }
        return res;
    }
}