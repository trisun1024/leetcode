import java.util.*;

class FindMostCompetitiveSubsequence {

    // Double-end Queue. Time = O(N); Space = O(N);
    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int count = nums.length - k;
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peekLast() > nums[i] && count > 0) {
                queue.pollLast();
                count--;
            }
            queue.offerLast(nums[i]);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.pollFirst();
        }
        return res;
    }
}