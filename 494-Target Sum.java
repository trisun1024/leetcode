class TargetSum {

    // DFS Brute Force
    public int findTargetSumWays(int[] nums, int S) {
        int[] count = new int[] { 0 };
        calculate(nums, 0, 0, S, count);
        return count[0];
    }

    private void calculate(int[] nums, int index, int sum, int S, int[] count) {
        if (index == nums.length) {
            if (sum == S) {
                count[0]++;
            }
        }
        calculate(nums, index + 1, sum + nums[index], S, count);
        calculate(nums, index + 1, sum - nums[index], S, count);
    }

    // 2-DP
    public int findTargetSumWaysI(int[] nums, int S) {
        int sum = 0;
        for (int i : nums)
            sum += i;
        if (S > sum || S < -sum)
            return 0;
        int[][] dp = new int[nums.length][sum * 2 + 1];
        dp[0][nums[0] + sum] = 1;
        dp[0][-nums[0] + sum] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = -sum; j <= sum; j++) {
                if (dp[i - 1][j + sum] != 0) {
                    int n = nums[i];
                    dp[i][j + sum + n] += dp[i - 1][j + sum];
                    dp[i][j + sum - n] += dp[i - 1][j + sum];
                }
            }
        }
        return dp[nums.length - 1][S + sum];
    }

    // 1-DP
    public int findTargetSumWaysII(int[] nums, int S) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum < S || S < -sum || (sum - S) % 2 == 1)
            return 0;
        int target = (sum - S) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}