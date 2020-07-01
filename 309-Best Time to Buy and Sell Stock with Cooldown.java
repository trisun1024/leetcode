// DP i i-1 i-2
// time O(n) space O(n)
class StockWithCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int[] hold = new int[n];
        int[] unhold = new int[n];

        hold[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            if (i == 1) {
                hold[i] = Math.max(hold[i - 1], -prices[i]);
            } else {
                hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i]);
            }
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i]);
        }

        return unhold[n - 1];
    }

    // Improved case, space O(1)
    public int maxProfitI(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int buy = Integer.MIN_VALUE, sell = 0, prev_buy = 0, prev_sell = 0;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }
}