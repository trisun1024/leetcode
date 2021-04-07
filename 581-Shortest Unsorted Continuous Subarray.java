import java.util.*;

class ShortestUnsortedContinuousSubarray {

    // Stack Time = O(N); Space = O(N);
    public int findUnsortedSubarray(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int l = nums.length, r = 0;
        // find left most point where is the first peak
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peekFirst()] > nums[i]) {
                l = Math.min(l, stack.pollFirst());
            }
            stack.offerFirst(i);
        }
        stack.clear();
        // find right most point where is the last peek
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peekFirst()] < nums[i]) {
                r = Math.max(r, stack.pollFirst());
            }
            stack.offerFirst(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }

    // One-pass using two pointers to simulate. Time = O(N); Space = O(1);
    public int findUnsortedSubarrayI(int[] nums) {
        int start = -1;
        int end = -1;
        int prevHigh = nums.length - 1;
        int prevLow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums[prevLow]) {
                end = i;
            } else {
                prevLow = i;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[prevHigh]) {
                start = i;
            } else {
                prevHigh = i;
            }
        }
        return (start >= 0 && end >= 0) ? (end - start) + 1 : 0;
    }

}