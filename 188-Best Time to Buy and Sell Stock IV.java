class StockIV {

    // DP with 2D array. Time = O(N^2); Space = O(N^2);
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        // base case. if len is less or equal than one, then return 0.
        if (len <= 1) {
            return 0;
        }
        // if k bigger than half of len, then we could use every increasing transcation.
        if (k >= len / 2) {
            return quickSolve(prices);
        }
        // build a 2D DP.
        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int curMax = -prices[0];
            for (int j = 1; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + curMax);
                curMax = Math.max(curMax, dp[i - 1][j - 1] - prices[j]);
            }
        }
        return dp[k][len - 1];
    }

    private int quickSolve(int[] array) {
        int len = array.length;
        int profit = 0;
        for (int i = 1; i < len; i++) {
            if (array[i] > array[i - 1]) {
                profit += array[i] - array[i - 1];
            }
        }
        return profit;
    }

    // DP with 1D array. Time = O(N^2); Space = O(N);
    public int maxProfitII(int k, int[] prices) {
        int len = prices.length;
        // base case. if len is less or equal than one, then return 0.
        if (len <= 1) {
            return 0;
        }
        // if k bigger than half of len, then we could use every increasing transcation.
        if (k >= len / 2) {
            return quickSolve(prices);
        }
        // build a 1D array.
        int[] dp = new int[len];
        for (int i = 0; i < k; i++) {
            int[] cur = new int[len];
            int curMax = -prices[0];
            for (int j = 1; j < len; j++) {
                cur[j] = Math.max(cur[j - 1], prices[j] + curMax);
                curMax = Math.max(curMax, dp[j - 1] - prices[j]);
            }
            dp = cur;
        }
        return dp[len - 1];
    }
}