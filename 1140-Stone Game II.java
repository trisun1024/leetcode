class StoneGameII {

    // DP
    public int stoneGameII(int[] piles) {
        if (piles == null || piles.length == 0) {
            return 0;
        }
        int n = piles.length;
        for (int i = n - 2; i >= 0; i--) {
            piles[i] += piles[i + 1];
        }
        if (n <= 2) {
            return piles[0];
        }
        int[][] dp = new int[n][(n + 1) / 2 + 1];
        for (int i = n - 1; i >= 0; i--) {
            int sum = piles[i];
            int m = (n - i + 1) / 2;
            dp[i][m] = sum;
            while (--m > 0) {
                dp[i][m] = 0;
                for (int x = 1; x <= m * 2 && i + x < n; x++) {
                    int mx = Math.min((n - i - x + 1) / 2, Math.max(x, m));
                    dp[i][m] = Math.max(dp[i][m], sum - dp[i + x][mx]);
                }
            }
        }
        return dp[0][1];
    }

    // DP with memorization
    public int stoneGameIIV(int[] piles) {
        if (piles == null || piles.length == 0) {
            return 0;
        }
        int n = piles.length;
        int[] prefix = new int[n];
        prefix[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            prefix[i] = prefix[i + 1] + piles[i];
        }
        int[][] dp = new int[n][n];
        return helper(piles, 0, 1, dp, prefix);
    }

    private int helper(int[] piles, int index, int M, int[][] dp, int[] prefix) {
        if (index >= piles.length) {
            return 0;
        }
        if (index + 2 * M >= piles.length) {
            return prefix[index];
        }
        if (dp[index][M] != 0) {
            return dp[index][M];
        }
        int val = 0;
        for (int i = index; i < index + 2 * M; i++) {
            val = Math.max(val, prefix[index] - helper(piles, i + 1, Math.max(M, i - index + 1), dp, prefix));
        }
        dp[index][M] = val;
        return val;
    }

}