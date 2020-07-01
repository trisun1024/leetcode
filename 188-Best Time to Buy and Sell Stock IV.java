class StockIV {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }
        if (k >= len / 2) {
            return quickSolve(prices);
        }
        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int curMax = -prices[0];
            for (int j = 1; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + curMax);
                System.out.println(dp[i][j - 1] + " " + dp[i][j]);
                curMax = Math.max(curMax, dp[i - 1][j - 1] - prices[j]);
                // System.out.print(curMax);
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

    // 
    public int maxProfitII(int k, int[] prices) {
        int n = prices.length;

        int maxprof = 0;
        if (k >= n) {
            for (int i = 1; i < n; i++) {
                maxprof += Math.max(0, prices[i] - prices[i - 1]);
            }
            return maxprof;
        }
        int[][] profit = new int[k + 1][n + 1];

        // day 0
        for (int t = 0; t <= k; t++) {
            profit[t][0] = 0;
        }

        // max 0 transaction
        for (int i = 0; i <= n; i++) {
            profit[0][i] = 0;
        }

        int maxval = 0;
        for (int t = 1; t <= k; t++) {

            int prevDiff = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                prevDiff = Math.max(prevDiff, profit[t - 1][i - 1] - prices[i - 1]);
                profit[t][i] = Math.max(prevDiff + prices[i - 1], profit[t][i - 1]);
            }
        }

        return profit[k][n];
    }
}