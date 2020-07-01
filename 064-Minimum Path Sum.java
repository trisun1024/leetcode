class MinimumPathSum {

    // DFS brute force T = O(4^(M+N)) Space = O(M*N)
    public int minPathSum(int[][] grid) {
        return calculate(grid, 0, 0);
    }

    private int calculate(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length)
            return Integer.MAX_VALUE;
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return grid[i][j];
        return grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
    }

    // DP 2-array
    public int minPathSumII(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // DP 1-array
    public int minPathSumIII(int[][] grid) {
        int[] dp = new int[grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length - 1 && j != grid[0].length - 1) {
                    dp[j] = grid[i][j] + dp[j + 1];
                } else if (j == grid[0].length - 1 && i != grid.length - 1) {
                    dp[j] = grid[i][j] + dp[j];
                } else if (j != grid[0].length - 1 && i != grid.length - 1) {
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                } else {
                    dp[j] = grid[i][j];
                }
            }
        }
        return dp[0];
    }
}