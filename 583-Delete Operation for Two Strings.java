
class DeleteOperationForTwoStrings {

    // DFS.
    public int minDistanceI(String word1, String word2) {
        int commonLen = findCommon(word1, word2, word1.length(), word2.length());
        return word1.length() - commonLen + word2.length() - commonLen;
    }

    private int findCommon(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return 1 + findCommon(s1, s2, m - 1, n - 1);
        else
            return Math.max(findCommon(s1, s2, m, n - 1), findCommon(s1, s2, m - 1, n));
    }

    // DP. Time = O(M*N); Space = O(M*N);
    public int minDistanceII(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return word1.length() + word2.length() - 2 * dp[word1.length()][word2.length()];
    }

    // DP without Longest Common Subarray. Time = O(M*N); Space = O(N);
    public int minDistance(String word1, String word2) {
        int[] dp = new int[word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            int[] temp = new int[word2.length() + 1];
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0 || j == 0) {
                    temp[j] = i + j;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    temp[j] = dp[j - 1];
                } else {
                    temp[j] = 1 + Math.min(dp[j], temp[j - 1]);
                }
            }
            dp = temp;
        }
        return dp[word2.length()];
    }
}
