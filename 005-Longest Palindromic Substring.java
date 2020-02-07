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
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        if (len == 1) {
            return s;
        }
        int[] max = { 1 };
        int[] index = { 0 };
        for (int i = 0; i < len - 1; i++) {
            if ((len - i) * 2 <= max[0]) {
                break;
            }
            expand(s, i, i, index, max);
            expand(s, i, i + 1, index, max);
        }
        return s.substring(index[0], index[0] + max[0]);
    }

    private void expand(String s, int i, int j, int[] index, int[] max) {
        if (s.charAt(i) != s.charAt(j)) {
            return;
        }
        while (i > 0 && j < s.length() - 1 && s.charAt(i - 1) == s.charAt(j + 1)) {
            i--;
            j++;
        }
        if (j - i + 1 > max[0]) {
            max[0] = j - i + 1;
            index[0] = i;
        }
    }
}