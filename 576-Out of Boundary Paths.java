import java.util.*;

class OutOfBoundaryPaths {

    private final int M = 1000000007;

    // Brute Force. Time = O(4^N);
    public int findPathsI(int m, int n, int N, int i, int j) {
        if (i == m || j == n || i < 0 || j < 0) {
            return 1;
        }
        if (N == 0) {
            return 0;
        }
        return findPathsI(m, n, N - 1, i - 1, j) + findPathsI(m, n, N - 1, i + 1, j) + findPathsI(m, n, N - 1, i, j - 1)
                + findPathsI(m, n, N - 1, i, j + 1);
    }

    // DFS + Memorization.

    public int findPathsII(int m, int n, int N, int i, int j) {
        int[][][] memo = new int[m][n][N + 1];
        for (int[][] x : memo) {
            for (int[] y : x) {
                Arrays.fill(y, -1);
            }
        }
        return helper(m, n, N, i, j, memo);
    }

    private int helper(int m, int n, int N, int i, int j, int[][][] memo) {
        if (i == m || j == n || i < 0 || j < 0) {
            return 1;
        }
        if (N == 0) {
            return 0;
        }
        if (memo[i][j][N] >= 0) {
            return memo[i][j][N];
        }
        memo[i][j][N] = ((helper(m, n, N - 1, i - 1, j, memo) + helper(m, n, N - 1, i + 1, j, memo)) % M
                + (helper(m, n, N - 1, i, j - 1, memo) + helper(m, n, N - 1, i, j + 1, memo)) % M) % M;
        return memo[i][j][N];
    }

    // DP. Time = O(N*m*n); Space = O(m*n);
    public int findPaths(int m, int n, int N, int x, int y) {
        int dp[][] = new int[m][n];
        dp[x][y] = 1;
        int count = 0;
        for (int moves = 1; moves <= N; moves++) {
            int[][] temp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == m - 1)
                        count = (count + dp[i][j]) % M;
                    if (j == n - 1)
                        count = (count + dp[i][j]) % M;
                    if (i == 0)
                        count = (count + dp[i][j]) % M;
                    if (j == 0)
                        count = (count + dp[i][j]) % M;
                    temp[i][j] = (((i > 0 ? dp[i - 1][j] : 0) + (i < m - 1 ? dp[i + 1][j] : 0)) % M
                            + ((j > 0 ? dp[i][j - 1] : 0) + (j < n - 1 ? dp[i][j + 1] : 0)) % M) % M;
                }
            }
            dp = temp;
        }
        return count;
    }
}