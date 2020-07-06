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
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;

        for (int curPrice : prices) {
            if (firstBuy < -curPrice)
                firstBuy = -curPrice; // the max profit after you buy first stock
            if (firstSell < firstBuy + curPrice)
                firstSell = firstBuy + curPrice; // the max profit after you sell it
            if (secondBuy < firstSell - curPrice)
                secondBuy = firstSell - curPrice; // the max profit after you buy the second stock
            if (secondSell < secondBuy + curPrice)
                secondSell = secondBuy + curPrice; // the max profit after you sell the second stock
        }

        return secondSell;
    }
}