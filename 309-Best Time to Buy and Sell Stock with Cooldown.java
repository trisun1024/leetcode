
class StockWithCooldown {

    // DP. Time = O(N); Space = O(N);
    public int maxProfitI(int[] prices) {
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

    // DP with improved space. Time = O(N); Space = O(1);
    public int maxProfit(int[] prices) {
        // sold: in this state, the agent has just sold a stock right before entering
        // this state. And the agent holds no stock at hand.
        int sold = Integer.MIN_VALUE;
        // held: in this state, the agent holds a stock that it bought at some point
        // before
        int held = Integer.MIN_VALUE;
        // first of all, one can consider this state as the starting point, where the
        // agent holds no stock and did not sell a stock before. More importantly, it is
        // also the transient state before the held and sold
        int reset = 0;
        for (int price : prices) {
            int preSold = sold;
            // sold[i]=hold[i−1]+price[i]
            sold = held + price;
            // held[i]=max(held[i−1],reset[i−1]−price[i])
            held = Math.max(held, reset - price);
            // reset[i]=max(reset[i−1],sold[i−1])
            reset = Math.max(reset, preSold);
        }
        return Math.max(sold, reset);
    }
}