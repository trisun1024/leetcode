import java.util.*;

class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[i] >= T[stack.peekFirst()]) {
                stack.pollFirst();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peekFirst() - i;
            stack.offerFirst(i);
        }
        return res;
    }
}