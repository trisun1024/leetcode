
class KInversePairsArray {

    // DP. Time = O(N^2*K); Space = O(N*K);
    public int kInversePairsI(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    for (int p = 0; p <= Math.min(j, i - 1); p++) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % 1000000007;
                    }
                }
            }
        }
        return dp[n][k];
    }

    // DP with Cumulative Sum. Time = O(N*K); Space = O(N*K);
    public int kInversePairsII(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        int M = 1000000007;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= i * (i - 1) / 2; j++) {
                if (i == 1 && j == 0) {
                    dp[i][j] = 1;
                    break;
                } else if (j == 0)
                    dp[i][j] = 1;
                else {
                    int val = (dp[i - 1][j] + M - ((j - i) >= 0 ? dp[i - 1][j - i] : 0)) % M;
                    dp[i][j] = (dp[i][j - 1] + val) % M;
                }
            }
        }
        return dp[n][k];
    }

    // DFS + Memorization. Time = O(N*K);
    public int kInversePairsIII(int n, int k) {
        Integer[][] memo = new Integer[1001][1001];
        int M = 1000000007;
        return ((inv(n, k, memo, M) + M - (k > 0 ? inv(n, k - 1, memo, M) : 0)) % M);
    }

    private int inv(int n, int k, Integer[][] memo, int M) {
        if (n == 0) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        if (memo[n][k] != null) {
            return memo[n][k];
        }
        int val = (inv(n - 1, k, memo, M) + M - ((k - n) >= 0 ? inv(n - 1, k - n, memo, M) : 0)) % M;
        memo[n][k] = (inv(n, k - 1, memo, M) + val) % M;
        return memo[n][k];
    }

    // 1D DP. Time = O(N*K); Space = O(K);
    public int kInversePairs(int n, int k) {
        int[] dp = new int[k + 1];
        int M = 1000000007;
        for (int i = 1; i <= n; i++) {
            int[] temp = new int[k + 1];
            temp[0] = 1;
            for (int j = 1; j <= k; j++) {
                int val = (dp[j] + M - ((j - i) >= 0 ? dp[j - i] : 0)) % M;
                temp[j] = (temp[j - 1] + val) % M;
            }
            dp = temp;
        }
        return ((dp[k] + M - (k > 0 ? dp[k - 1] : 0)) % M);
    }
}