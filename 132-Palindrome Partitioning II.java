class PalindromePartitioningII {

    // DP with palindrome check
    public int minCut(String s) {
        int len = s.length();
        if (len <= 1) {
            return 0;
        }
        int[] dp = new int[len];
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            dp[i] = i;
            if (isPalindrome(s, 0, i)) {
                dp[i] = 0;
                continue;
            }
            for (int j = 1; j <= i; j++) {
                if (isPalindrome(s, j, i)) {
                    dp[i] = Math.min(dp[j - 1] + 1, dp[i]);
                }
            }
        }
        return dp[len - 1];
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}