
class FindDerangementOfAnArray {

    // Recursion. Time = O(N); Space = O(N);
    public int findDerangementI(int n) {
        Integer[] memo = new Integer[n + 1];
        return helper(n, memo);
    }

    private int helper(int n, Integer[] memo) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 0;
        }
        if (memo[n] != null) {
            return memo[n];
        }
        memo[n] = (int) (((n - 1L) * (helper(n - 1, memo) + helper(n - 2, memo))) % 1000000007);
        return memo[n];
    }

    // DP. Time = O(N); Space = O(N);
    public int findDerangement(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = (int) (((i - 1L) * (dp[i - 1] + dp[i - 2])) % 1000000007);
        }
        return dp[n];
    }

    // DP Simplified.
    public int findDerangementII(int n) {
        long dn1 = 0, dn2 = 1;
        long M = 1000000007;
        long res = n == 1 ? 0 : 1;
        for (int i = 3; i <= n; i++) {
            res = ((i - 1) * (dn1 + dn2)) % M;
            dn1 = dn2;
            dn2 = res;
        }
        return (int) res;
    }

    // Formula.
    public int findDerangementIII(int n) {
        long mul = 1, sum = 0, M = 1000000007;
        for (int i = n; i >= 0; i--) {
            sum = (sum + M + mul * (i % 2 == 0 ? 1 : -1)) % M;
            mul = (mul * i) % M;
        }
        return (int) sum;
    }
}