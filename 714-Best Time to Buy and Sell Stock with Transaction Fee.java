
class BestTimeToBuyAndSellStockWithTransactionFee {

    // DP.
    public int maxProfit(int[] prices, int fee) {
        // In day(0), cash(0) (stands for initial profit) is 0, hold(0) is -prices[0]
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // cash(i) = max(cash(i-1), hold(i-1) + prices[i] - fee)
            // cash(i-1) => not holding stock at the end of day(i-1), and do nothing on
            // day(i)
            // hold(i-1) + prices[i] - fee => hold the stock at the end of day(i-1), sell it
            // on day(i)
            // NOTE: so cash(i) >= cash(i-1)
            cash = Math.max(cash, hold + prices[i] - fee);

            // hold(i) = max(hold(i-1), cash(i) - prices[i])
            // hold(i-1) => holding stock at the end of day(i-1), and do nothing on day(i)
            // cash(i) - prices[i] => because cash(i) >= cash(i-1), if selling stock on
            // day(i) results in more cash in hand than cash(i-1),
            // it makes sense to sell it on day(i) and buy it back instead of selling it on
            // day(i-1) and buy it on day(i).
            // Take the following case as an example:
            // service fee: 1
            // prices: [5, 10, 15, ...]
            // cash[0, 4, 9, ...]
            // hold[-5, -5, ?, ...]
            // Now consider all possible values of hold(2):
            // 1. Do nothing: a = hold(1) = -5
            // 2. Sell on day(1), buy on day(2): b = cash(1) - price(2) = 4 - 15 = -9
            // 3. Sell on day(2), and buy again: c = hold(1) + p(2) - fee - p(2) = -5 + 15 -
            // 1 - 15 = -6
            // c > b, so buying on day(2) results in more profit.
            // However, since a = -5, which is a better choice, finally, hold(2) is resolved
            // to "-5", which is obvious because the price is keeping
            // rising, so the best choice would be buy it on day(0) and hold it.
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }

    // Greedy.
    public int maxProfitI(int[] prices, int fee) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int ans = 0;
        int min = prices[0];
        for (int i = 1; i < len; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] > min + fee) {
                ans += prices[i] - fee - min;
                min = prices[i] - fee;
            }
        }
        return ans;
    }
}