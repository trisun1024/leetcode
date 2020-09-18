class IntegerBreak {

    // DP. Time = O(N^2);
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <=i ;j++) {
                if(i+j <=n) {
                    dp[i+j] = Math.max(Math.max(dp[i], i) * Math.max(dp[j],j) , dp[i+j]);
                }
            }
        }
        return dp[n];
    }

    // DP + Memorization
    public int integerBreakI(int n) {
        if(n <= 1) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        return helper(n, dp);
    }

    private int helper(int n , int[] dp) {
        if(n==0) {
            return 1;
        }
        if(dp[n] > 0) {
            return dp[n];
        }
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < n ;i++) {
            max = Math.max(max, Math.max(i*helper(n-i, dp) , i*(n-i)));
        }
        return dp[n] = max;
    }
}