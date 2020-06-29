class Solution {

    // DP Solution. Time = O(N); Space = O(1);
    /*
     * base case: M.length = N+2;
     * M[0] = 0;
     * M[1] = 0;
     * induction rule:
     * M[i] represents maximum from M[current index-1 position] or M[current index-2 position] + current position * value;
     * M[i] = max(M[i-1], M[i-2]+nums[i-2]);
     */

    public int robI(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 2];
        for (int i = 0; i < n; i++) {
            dp[i+2] = Math.max(dp[i+1], nums[i] + dp[i]);
        }
        return dp[n + 1];
    }

    public int rob(int[] nums) {
        int prevMax = 0;
        int curMax = 0;
        for (int n : nums) {
            int tmp = curMax;
            curMax = Math.max(prevMax + n, curMax);
            prevMax = tmp;
        }
        return curMax;
    }
}