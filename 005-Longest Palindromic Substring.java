import java.util.*;

class Solution {

    public String longestPalindromeDP(String s) {
        int n = s.length();
        if (n == 0) {
            return "";
        }
        boolean[][] dp = new boolean[n][n];
        int max = 0;
        int index = 0;
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (i == j - 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                }
                if (dp[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    index = i;
                }
            }
        }
        return s.substring(index, index + max);
    }

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
        for (int i = len - 1; i >= 0; i--) {

            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (s.charAt(i) == s.charAt(j) && (j - i == 1 || dp[i + 1][j - 1] > 0)) {
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

    // Advanced Solution: Manachers Algorithm
    public String findLongestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] arr = addBoundaries(s.toCharArray());
        int[] p = new int[arr.length];
        int c = 0, r = 0; // Here the first element in s2 has been processed.
        int m = 0, n = 0; // The walking indices to compare if two elements are the same.
        for (int i = 1; i < arr.length; i++) {
            if (i > r) {
                p[i] = 0;
                m = i - 1;
                n = i + 1;
            } else {
                int i2 = c * 2 - i;
                if (p[i2] < (r - i - 1)) {
                    p[i] = p[i2];
                    m = -1; // This signals bypassing the while loop below.
                } else {
                    p[i] = r - i;
                    n = r + 1;
                    m = i * 2 - n;
                }
            }
            while (m >= 0 && n < arr.length && arr[m] == arr[n]) {
                p[i]++;
                m--;
                n++;
            }
            if ((i + p[i]) > r) {
                c = i;
                r = i + p[i];
            }
        }
        int len = 0;
        c = 0;
        for (int i = 1; i < arr.length; i++) {
            if (len < p[i]) {
                len = p[i];
                c = i;
            }
        }
        char[] ss = Arrays.copyOfRange(arr, c - len, c + len + 1);
        return String.valueOf(removeBoundaries(ss));
    }

    private char[] addBoundaries(char[] cs) {
        if (cs == null || cs.length == 0)
            return "||".toCharArray();

        char[] cs2 = new char[cs.length * 2 + 1];
        for (int i = 0; i < (cs2.length - 1); i = i + 2) {
            cs2[i] = '|';
            cs2[i + 1] = cs[i / 2];
        }
        cs2[cs2.length - 1] = '|';
        return cs2;
    }

    private char[] removeBoundaries(char[] cs) {
        if (cs == null || cs.length < 3)
            return "".toCharArray();

        char[] cs2 = new char[(cs.length - 1) / 2];
        for (int i = 0; i < cs2.length; i++) {
            cs2[i] = cs[i * 2 + 1];
        }
        return cs2;
    }
}