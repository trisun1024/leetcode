class StockIII {

    // Two DPs. Time = O(N); Space = O(N);
    public int maxProfitI(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int len = prices.length;
        int[] left = new int[len];
        int[] right = new int[len + 1];
        int min = prices[0];
        int max = prices[len - 1];
        for (int i = 1; i < len; i++) {
            // find left and update min
            left[i] = Math.max(left[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
            // find right and update max
            int r = len - 1 - i;
            right[r] = Math.max(right[r + 1], max - prices[r]);
            max = Math.max(max, prices[r]);
        }
        int maxProfit = 0;
        for (int i = 0; i < len; i++) {
            maxProfit = Math.max(maxProfit, left[i] + right[i + 1]);
        }
        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        // the minimal cost of buying the stock in transaction #1. The minimal cost to
        // acquire a stock would be the minimal price value that we have seen so far at
        // each step.
        int firstBuy = Integer.MAX_VALUE;
        // the maximal profit of selling the stock in transaction #1. Actually, at the
        // end of the iteration, this value would be the answer for the first problem in
        // the series
        int firstSell = 0;
        // the minimal cost of buying the stock in transaction #2, while taking into
        // account the profit gained from the previous transaction #1. One can consider
        // this as the cost of reinvestment. Similar with t1_cost, we try to find the
        // lowest price so far, which in addition would be partially compensated by the
        // profits gained from the first transaction
        int secondBuy = Integer.MAX_VALUE;
        // the maximal profit of selling the stock in transaction #2. With the help of
        // t2_cost as we prepared so far, we would find out the maximal profits with at
        // most two transactions at each step.
        int secondSell = 0;
        for (int curPrice : prices) {
            firstBuy = Math.min(firstBuy, curPrice);
            firstSell = Math.max(firstSell, curPrice - firstBuy);
            secondBuy = Math.min(secondBuy, curPrice - firstSell);
            secondSell = Math.max(secondSell, curPrice - secondBuy);
        }
        return secondSell;
    }
}