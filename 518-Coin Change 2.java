class CoinChange2 {

    // DP 
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int x = coin; x < amount + 1; x++) {
                dp[x] += dp[x - coin];
            }
        }
        return dp[amount];
    }

    // DP 2 Dim
    public int changeII(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1; // have 1 way to change 0 amount
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j]; // skip ith coin
                if (j >= coins[i - 1])
                    dp[i][j] += dp[i][j - coins[i - 1]]; // use ith coin
            }
        }
        return dp[n][amount];
    }
}