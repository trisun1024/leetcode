 
class GuessNumberHigherOrLowerII {

    // DP.
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 2][n + 2];

        for (int len = 2; len <= n; len++) {
            // i from [1, n - 1], need to make sure n - (min(len)) + 1 is in range
            for (int i = 1; i <= n - len + 1; i++) {
                // j from [2, n], len starts from 2 makes sure i and j is at least one dist out
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                // k = [1, n]
                for (int k = i; k <= j; k++) {
                    // k + 1 = [2, n + 1], hence need n + 2 size array
                    int res = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                    dp[i][j] = Math.min(dp[i][j], res);
                }
            }
        }
        return dp[1][n];
    }

    // DFS
   public int getMoneyAmountI(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return cost(dp, 1, n);
    }
    
   private int cost(int[][] dp, int start, int end) {
        if (start >= end) {
            return 0;
        }
        if (dp[start][end] != 0) {
            return dp[start][end];
        }
        int res = Integer.MAX_VALUE;
        for (int i = start + (end - start) / 2; i <= end; i++) {
            int tmp = i + Math.max(cost(dp, start, i - 1), cost(dp, i + 1, end));
            res = Math.min(res, tmp);
        }
        dp[start][end] = res;
        return res;
    }

}