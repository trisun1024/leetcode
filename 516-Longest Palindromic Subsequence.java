
class LongestPalindromicSubsequence {

    // 2D DP.
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    // 1D DP.
    public int longestPalindromeSubseqI(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            int cur = 0;
            for (int j = i - 1; j >= 0; j--) {
                int prev = dp[j];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = cur + 2;
                }
                cur = Math.max(prev, cur);
            }
        }

        for (int i : dp) {
            max = Math.max(max, i);
        }

        return max;
    }
}