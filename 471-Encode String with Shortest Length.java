
class EncodeStringwithShortestLength {

    // Time = O(N^3); Space = O(N^2);
    public String encodeI(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        String[][] dp = new String[n][n];
        for (int len = 0; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                String sub = s.substring(i, j + 1);
                dp[i][j] = sub;
                if (len < 4) {
                    continue;
                }
                for (int k = 1; k < j; k++) {
                    if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                }
                // check if subStr has repeat pattern
                for (int k = i; k < j; k++) {
                    String repeat = s.substring(i, k + 1);
                    if (sub.length() % (k - i + 1) == 0 && sub.replaceAll(repeat, "").length() == 0) {
                        String ss = sub.length() / repeat.length() + "[" + dp[i][k] + "]";
                        if (ss.length() < dp[i][j].length())
                            dp[i][j] = ss;
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    // DP.
    public String encode(String s) {
        int n = s.length();

        if (s.length() <= 3) {
            return s;
        }

        // dp[i][j]表示s[i...j]中最短的encoded string
        String[][] dp = new String[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                dp[i][j] = s.substring(i, i + len);

                // No need to encode if the current len of substring is less than 4
                if (len <= 3) {
                    continue;
                }

                for (int k = i; k < j; k++) {
                    String left = dp[i][k];
                    String right = dp[k + 1][j];

                    if (left.length() + right.length() < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                }

                String collapsedStr = collapse(dp, s.substring(i, i + len), i);
                if (collapsedStr.length() < dp[i][j].length()) {
                    dp[i][j] = collapsedStr;
                }
            }
        }
        return dp[0][n - 1];
    }

    public String collapse(String[][] dp, String s, int start) {
        // Check if there is repeated pattern in s
        int index = (s + s).indexOf(s, 1);

        // No repeated pattern in this case
        if (index >= s.length()) {
            return s;
        } else {
            return (s.length() / index) + "[" + dp[start][start + index - 1] + "]";
        }
    }
}