import java.util.*;

class Solution {

    // Recursion + Memorization
    private HashMap<Pair<Integer, Integer>, Integer> memo;

    private int recurse(String s, String t, int i, int j) {

        int M = s.length();
        int N = t.length();

        // Base case
        if (i == M || j == N || M - i < N - j) {
            return j == t.length() ? 1 : 0;
        }

        Pair<Integer, Integer> key = new Pair<Integer, Integer>(i, j);

        // Check to see if the result for this recursive
        // call is already cached
        if (this.memo.containsKey(key)) {
            return this.memo.get(key);
        }

        // Always calculate this result since it's
        // required for both the cases
        int ans = this.recurse(s, t, i + 1, j);

        // If the characters match, then we make another
        // recursion call and add the result to "ans"
        if (s.charAt(i) == t.charAt(j)) {
            ans += this.recurse(s, t, i + 1, j + 1);
        }

        // Cache the result
        this.memo.put(key, ans);
        return ans;
    }

    public int numDistinctI(String s, String t) {

        this.memo = new HashMap<Pair<Integer, Integer>, Integer>();
        return this.recurse(s, t, 0, 0);
    }

    // DP 2-Dim DP. Time = O(M*N); Space = O(M*N);
    /*
     * int m = s.length; int n = t.length; M[i,j] represents number of subsequences
     * in string s [i, m-1] equals to string t [j, n-1]; base case M[i, n] = 1;
     * because every string has at least one subsequence which is a empty string
     * M[m, j] = 0; every empty string in s won't have matches to subsequence of
     * string t, except empty to empty induction rule M[i][j] = M[i+1][j] update
     * previous counts if current index i and j is matched, update the count sum
     * M[i][j] += M[i+1][j+1];
     * 
     */
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    // DP 1-Dim DP. Time = O(M*N); Space = O(M);
    public int numDistinctII(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[] dp = new int[n];
        int prev = 1;
        for (int i = m - 1; i >= 0; i--) {
            prev = 1;
            for (int j = n - 1; j >= 0; j--) {
                int old = dp[j];
                if (s.charAt(i) == t.charAt(j)) {
                    dp[j] += prev;
                }
                prev = old;
            }
        }
        return dp[0];
    }
}