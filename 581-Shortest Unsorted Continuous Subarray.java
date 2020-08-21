import java.util.*;

class ShortestUnsortedContinuousSubarray {

    // Stack Time = O(N); Space = O(N);
    public int findUnsortedSubarray(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peekFirst()] > nums[i]) {
                l = Math.min(l, stack.pollFirst());
            }
            stack.offerFirst(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peekFirst()] < nums[i]) {
                r = Math.max(r, stack.pollFirst());
            }
            stack.offerFirst(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }

    // One-pass Time = O(N); Space = O(1);
    public int findUnsortedSubarrayI(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                flag = true;
            }
            if (flag) {
                min = Math.min(min, nums[i]);
            }
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                flag = true;
            }
            if (flag) {
                max = Math.max(max, nums[i]);
            }
        }
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }
}