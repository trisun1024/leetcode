class isSubsequence {

    // two pointers
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    // dp
    public boolean isSubsequenceII(String s, String t) {
        if (s.length() == 0 && t.length() == 0) {
            return true;
        }
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int row = 1; row <= t.length(); row++) {
            for (int col = 1; col <= s.length(); col++) {
                // if match then previous + 1 
                if (s.charAt(col - 1) == t.charAt(row - 1)) {
                    dp[col][row] = dp[col - 1][row - 1] + 1;
                } else { // find the max between 
                    dp[col][row] = Math.max(dp[col - 1][row], dp[col][row - 1]);
                }
            }
            if (dp[s.length()][row] == s.length()) {
                return true;
            }
        }
        return false;
    }

}