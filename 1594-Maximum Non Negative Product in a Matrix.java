import java.util.*;

class MaximumNonNegativeProductInMatrix {

    // DP. Time = O(M*N);
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int mod = 1_000_000_007;
        long[][][] dp = new long[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = new long[] { grid[i][j], grid[i][j] };
                } else if (i == 0) {
                    dp[i][j][0] = dp[i][j][1] = grid[i][j] * dp[i][j - 1][0];
                } else if (j == 0) {
                    dp[i][j][0] = dp[i][j][1] = grid[i][j] * dp[i - 1][j][0];
                } else {
                    long a = grid[i][j] * Math.max(dp[i][j - 1][0], dp[i - 1][j][0]);
                    long b = grid[i][j] * Math.min(dp[i][j - 1][1], dp[i - 1][j][1]);
                    dp[i][j][0] = Math.max(a, b);
                    dp[i][j][1] = Math.min(a, b);
                }
            }
        }
        return dp[m - 1][n - 1][0] < 0 ? -1 : (int) ((dp[m - 1][n - 1][0]) % mod);
    }

    // DFS.
    public int maxProductPathI(int[][] grid) {
        int mod = 1000000007;
        long[] product = new long[] { -1 };
        dfs(grid, 0, 0, grid[0][0], product);
        return (int) (product[0] % mod);
    }

    private void dfs(int[][] grid, int i, int j, long cur, long[] product) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            product[0] = Math.max(product[0], cur);
            return;
        }
        if (grid[i][j] == 0) {
            product[0] = Math.max(product[0], 0);
            return;
        }
        if (i + 1 < grid.length) {
            dfs(grid, i + 1, j, cur * grid[i + 1][j], product);
        }
        if (j + 1 < grid[0].length) {
            dfs(grid, i, j + 1, cur * grid[i][j + 1], product);
        }
    }
}