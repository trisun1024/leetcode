class MinimumWindowSubsequence {

    // DP 2 array
    public String minWindowII(String S, String T) {
        int m = T.length();
        int n = S.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (dp[m][i] != 0 && i - dp[m][i] + 1 < minLen) {
                start = dp[m][i] - 1;
                minLen = i - dp[m][i] + 1;
            }
        }
        return minLen > n ? "" : S.substring(start, start + minLen);
    }

    // DP 1 array
    public String minWindow(String S, String T) {
        int m = T.length();
        int n = S.length();
        int[] startIndex = new int[n + 1];

        // initialize assumming first will match
        for (int i = 0; i <= n; i++) {
            startIndex[i] = i + 1;
        }

        for (int i = 1; i <= m; i++) {
            // temp is current row
            int[] temp = new int[n + 1];

            for (int j = 1; j <= n; j++) {
                // when current start index is valid, use the start index from previous digit
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    temp[j] = startIndex[j - 1];

                    // otherwise record the start index
                } else {
                    temp[j] = temp[j - 1];

                }
            }
            // update when finish each row
            startIndex = temp;
        }

        // find the shortest one
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (startIndex[i] != 0 && i - startIndex[i] + 1 < minLength) {
                minLength = i - startIndex[i] + 1;
                start = startIndex[i] - 1;
            }
        }

        return minLength > n ? "" : S.substring(start, start + minLength);
    }

    // Two Pointers
    public String minWindowIII(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int sindex = 0, tindex = 0, slen = s.length, tlen = t.length, start = -1, len = slen;
        while (sindex < slen) {
            if (s[sindex] == t[tindex]) {
                if (++tindex == tlen) {
                    // check feasibility from left to right of T
                    int end = sindex + 1;
                    // check optimization from right to left of T
                    while (--tindex >= 0) {
                        while (s[sindex--] != t[tindex])
                            ;
                    }
                    ++sindex;
                    ++tindex;
                    // record the current smallest candidate
                    if (end - sindex < len) {
                        len = end - sindex;
                        start = sindex;
                    }
                }
            }
            ++sindex;
        }
        return start == -1 ? "" : S.substring(start, start + len);
    }

}