class BurstBalloons {

    // DP top-down. Time = O(N^3); Space = O(N^2);
    public int maxCoinsI(int[] nums) {
        int n = nums.length + 2;
        // form a new array with left most and right most value of 1
        int[] new_nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            new_nums[i + 1] = nums[i];
        }
        new_nums[0] = new_nums[n - 1] = 1;

        // cache the results of dp
        int[][] memo = new int[n][n];

        // find the maximum number of coins obtained from adding all balloons from (0,
        // len(nums) - 1)
        return dp(memo, new_nums, 0, n - 1);
    }

    public int dp(int[][] memo, int[] nums, int left, int right) {
        // no more balloons can be added
        if (left + 1 == right) {
            return 0;
        }

        // we've already seen this, return from cache
        if (memo[left][right] > 0) {
            return memo[left][right];
        }

        // add each balloon on the interval and return the maximum score
        int ans = 0;
        for (int i = left + 1; i < right; ++i) {
            ans = Math.max(ans,
                    nums[left] * nums[i] * nums[right] + dp(memo, nums, left, i) + dp(memo, nums, i, right));
        }
        // add to the cache
        memo[left][right] = ans;
        return ans;
    }

    // DP bottom-up. Time = O(N^3); Space = O(N^2);
    public int maxCoins(int[] nums) {
        // Rebuild the nums to n+2 length with start and end value of 1
        int n = nums.length + 2;
        int[] newArray = new int[n];
        for (int i = 0; i < nums.length; i++) {
            newArray[i + 1] = nums[i];
        }
        newArray[0] = newArray[n - 1] = 1;
        // DP run the result.
        int[][] dp = new int[n][n];
        for (int left = n - 2; left >= 0; left--) {
            for (int right = left + 2; right < n; right++) {
                for (int i = left + 1; i < right; i++) {
                    dp[left][right] = Math.max(dp[left][right],
                            newArray[left] * newArray[i] * newArray[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        return dp[0][n - 1];
    }
    /**
     * Base Case: DP[1][1] = left * (i=1) * right
     * 
     * Induction Rule: DP[left][right] represents the maximum value from left index
     * to right index inclusive.
     */
}