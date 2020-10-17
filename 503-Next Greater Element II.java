import java.util.*;

class NextGreaterElementII {

    // Stack. Time = O(N); Space = O(N);
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peekFirst()] <= nums[i % nums.length]) {
                stack.pollFirst();
            }
            res[i % nums.length] = stack.isEmpty() ? -1 : nums[stack.peekFirst()];
            stack.offerFirst(i % nums.length);
        }
        return res;
    }

    public int[] nextGreaterElementsI(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peekFirst()] < num) {
                res[stack.pollFirst()] = num;
            }
            if (i < n) {
                stack.offerFirst(i);
            }
        }
        return res;
    }
}