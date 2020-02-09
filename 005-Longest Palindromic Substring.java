import java.util.*;

class Solution {

    // DP
    // m[i][j] represents the longest palindromic substring in s[i:j], is
    // paldinrome, and m[i][j] represent maximum length
    // base case m[i][j] = 1
    // induction rule:
    // case 1: s[i:j] is not palindrome m[i][j] = 0
    // case 2: s[i:j] is palindrome m[i][j] = m[i+1][j-1] if(j-i==1 ||
    // m[i+1][j-1]>0)
    // Time O(N^2) Space O(N^2)

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        int[][] dp = new int[len][len];
        int max = 1;
        int index = 0;
        for (int i = len - 1; i >= 0; i++) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i == 1 || dp[i + 1][j - 1] > 0)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    index = i;
                }
            }
        }
        return s.substring(index, index + max);
    }

    // traverse and expand
    // Time O(N^2) Space O(1)
    public String longestPalindromeII(String s) {
        if (s == null || s.length() < 1)
            return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}