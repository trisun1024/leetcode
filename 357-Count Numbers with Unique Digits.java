class CountNumbersWithUniqueDigits {

    // Math.
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int prev2 = 10;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int output = prev2 + (prev2 - prev1) * (11 - i);
            prev1 = prev2;
            prev2 = output;
        }
        return prev2;
    }

    // DP
    public int countNumbersWithUniqueDigitsI(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int product = 9;
        for (int i = 1; i <= n; i++) {
            dp[i] = product + dp[i - 1];
            product = product * (10 - i);
        }
        return dp[n];
    }
}