
class LastStoneWeight {

    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += stones[i];
        }
        int S2 = 0;
        boolean[] dp = new boolean[total + 1]; // use only 1D array to store the status
        dp[0] = true;
        for (int s : stones) {
            boolean[] cur = dp.clone();
            for (int i = s; i <= total / 2; i++) { // wont affect the value that is smaller than current stone's weight
                if (dp[i - s]) {
                    cur[i] = true;
                    S2 = Math.max(S2, i);
                }
            }
            dp = cur;
        }
        return total - 2 * S2;
    }
}