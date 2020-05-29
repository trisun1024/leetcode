import java.util.*;

class Solution {

    // use stack and dp to solve this problem Time = O(N) Space = O(N)
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= heights.length; i++) {
            int cur = i == heights.length ? 0 : heights[i];
            while (!stack.isEmpty() && cur <= heights[stack.peekFirst()]) {
                int height = heights[stack.pollFirst()];
                int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
                res = Math.max(res, height * (i - left));
            }
            stack.offerFirst(i);
        }
        return res;
    }
}