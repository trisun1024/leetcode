class Solution {

    // brute force dfs
    // Time O(2^(M+N)) Space O(M+N)
    public boolean isInterleave(String s1, String s2, String s3) {
        return helper(s1, 0, s2, 0, s3, "");
    }

    private boolean helper(String s1, int i, String s2, int j, String s3, String res) {
        if (res.equals(s3) && i == s1.length() && j == s2.length()) {
            return true;
        }
        boolean flag = false;
        if (i < s1.length()) {
            flag |= helper(s1, i + 1, s2, j, s3, res + s1.charAt(i));
        }
        if (j < s2.length()) {
            flag |= helper(s1, i, s2, j + 1, s3, res + s2.charAt(j));
        }
        return flag;
    }

    // recursion in memorization
    // Time O(2^(M+N)) Space O(M+N)
    public boolean isInterleaveII(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] memo = new int[len1][len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                memo[i][j] = -1;
            }
        }
        return dfs(s1, 0, s2, 0, s3, 0, memo);
    }

    private boolean dfs(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        if (s1.length() == i) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if (s2.length() == j) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if (memo[i][j] >= 0) {
            return memo[i][j] == 1 ? true : false;
        }
        boolean flag = false;
        if (s3.charAt(k) == s1.charAt(i) && dfs(s1, i + 1, s2, j, s3, k + 1, memo)
                || s3.charAt(k) == s2.charAt(j) && dfs(s1, i, s2, j + 1, s3, k + 1, memo)) {
            flag = true;
        }
        memo[i][j] = flag ? 1 : 0;
        return flag;
    }

    // DP 2 dimension
    public boolean isInterleaveIII(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // DP 1 dimension
    // Time O(M*N) Space O(N)
    public boolean isInterleaveIV(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[] = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s2.length()];
    }

}