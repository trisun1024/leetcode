class StockII {

    // One Pass. Time = O(N);
    public int maxProfit(int[] prices) {
        // think the peaks
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        }
        return profit;
    }
}