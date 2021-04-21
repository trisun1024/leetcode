import java.util.*;

class LongestIncreasingSubsequence {

    // DP. Time = O(N^2); Space = O(N);
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] longest = new int[nums.length];
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            longest[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    longest[i] = Math.max(longest[j] + 1, longest[i]);
                }
            }
            res = Math.max(longest[i], res);
        }
        return res;
    }

    // DP + Binary Search. Time = O(N*log(N));
    public int lengthOfLISI(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            // Arrays.binarySearch() method returns index of the search key, if it is
            // contained in the array, else it returns (-(insertion point) - 1)
            int j = Arrays.binarySearch(dp, 0, len, nums[i]);
            if (j < 0) {
                j = -(j + 1);
            }
            dp[j] = nums[i];
            if (j == len) {
                len++;
            }
        }
        return len;
    }
}