class RegularExpressionMatch {

    // Recursion.
    public boolean isMatch(String s, String p) {
        if (p.isEmpty())
            return s.isEmpty();

        boolean firstMatch = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p)));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    // DP + Bottom-up.
    public boolean isMatchI(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;

        // for (int i = 1; i <= m; i++) {
        // dp[i][0] = false;
        // }

        for (int j = 1; j <= n; j++) {
            if (j > 1 && p.charAt(j - 1) == '*') {
                // * can be used to match zero previous char
                // and may be used to match empty string
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (j == 1)
                        continue;
                    // option 1: use '*' to match zero previous char
                    // "" -> a*
                    dp[i][j] |= dp[i][j - 2];

                    // option 2: use '*' to match multiple (>= 1) previous char

                    // a -> a*
                    // aa -> a*
                    // aaa -> a*
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    // DP + Top-Down.
    public boolean isMatchII(String s, String p) {
        Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];
        return isMatch(s, 0, p, 0, dp);
    }

    private boolean isMatch(String s, int i, String p, int j, Boolean[][] dp) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        boolean res;
        if (j == p.length()) {
            res = (i == s.length());
        } else {
            boolean isFirstMatch = (i < s.length() && j < p.length()
                    && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                res = isMatch(s, i, p, j + 2, dp) || (isFirstMatch && isMatch(s, i + 1, p, j, dp));
            } else {
                res = isFirstMatch && isMatch(s, i + 1, p, j + 1, dp);
            }
        }
        dp[i][j] = res;
        return res;
    }
}