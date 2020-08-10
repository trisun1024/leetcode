
class PaintFence {

    // DP. Time = O(N); Space = O(N);
    public int numWaysI(int n, int k) {
        if (n == 0)
            return 0;
        if (n == 1)
            return k;
        int[] dp = new int[n];
        dp[0] = k;
        dp[1] = k * k;
        for (int i = 2; i < n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1);
        }
        return dp[n - 1];
    }

    // DP. Time = O(N); Space = O(1);
    public int numWays(int n, int k) {
        if (n == 0)
            return 0;
        if (n == 1)
            return k;
        int num1 = k, num2 = k * k;
        for (int i = 2; i < n; i++) {
            int tmp = num2;
            num2 = (num1 + num2) * (k - 1);
            num1 = tmp;
        }
        return num2;
    }
}