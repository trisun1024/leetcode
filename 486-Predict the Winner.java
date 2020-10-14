
class PredictWinner {

    // Recursion.
    public boolean PredictTheWinnerI(int[] nums) {
        return helper(nums, 0, nums.length - 1, 1) >= 0;
    }

    private int helper(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return turn * nums[start];
        }
        int left = turn * nums[start] + helper(nums, start + 1, end, -turn);
        int right = turn * nums[end] + helper(nums, start, end - 1, -turn);
        return turn * Math.max(turn * left, turn * right);
    }

    // 2D DP. Time = O(N^2); Space = O(N^2);
    // 1.
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        int tot = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                    tot += nums[i];
                } else if (i + 1 == j) {
                    dp[i][j] = Math.max(nums[i], nums[j]);
                } else {
                    int maxLeft = nums[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
                    int maxRight = nums[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]);
                    dp[i][j] = Math.max(maxLeft, maxRight);
                }
            }
        }
        return dp[0][n - 1] >= tot - dp[0][n - 1];
    }

    // 1D DP. Time = O(N^2); Space = O(N);
    public boolean PredictTheWinnerII(int[] nums) {
        int[] dp = new int[nums.length];
        for (int s = nums.length - 1; s >= 0; s--) {
            dp[s] = nums[s];
            for (int e = s + 1; e < nums.length; e++) {
                int a = nums[s] - dp[e];
                int b = nums[e] - dp[e - 1];
                dp[e] = Math.max(a, b);
            }
        }
        return dp[nums.length - 1] >= 0;
    }
}