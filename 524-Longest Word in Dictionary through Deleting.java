import java.util.*;

class LongestWordInDictionaryThroughDeleting {

    // Time = O(N^2);
    public String findLongestWordI(String s, List<String> d) {
        String maxStr = "";
        for (String str : d) {
            if (isSubsequence(str, s)) {
                if (str.length() > maxStr.length() || (str.length() == maxStr.length() && str.compareTo(maxStr) < 0)) {
                    maxStr = str;
                }
            }
        }
        return maxStr;
    }

    private boolean isSubsequence(String x, String y) {
        int i = 0;
        for (int j = 0; j < y.length() && i < x.length(); j++) {
            if (x.charAt(i) == y.charAt(j)) {
                i++;
            }
        }
        return i == x.length();
    }

    // DP.
    public String findLonString(String s, List<String> d) {
        int len = s.length();
        String res = "";
        int[][] dp = new int[len + 1][26];
        Arrays.fill(dp[len], -1);
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                dp[i][j] = dp[i][j - 1];
            }
            dp[i][s.charAt(i) - 'a'] = i + 1;
        }

        for (String str : d) {
            if (s.length() >= str.length() && isSubSeq(dp, str)) {
                if (str.length() > res.length()) {
                    res = str;
                }
                if (str.length() == res.length()) {
                    if (str.compareTo(res) < 0) {
                        res = str;
                    }
                }
            }

        }
        return res;
    }

    private boolean isSubSeq(int[][] indexArray, String str) {
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (indexArray[j][str.charAt(i) - 'a'] == -1) {
                return false;
            }
            j = indexArray[j][str.charAt(i) - 'a'];
        }
        return true;
    }

}
