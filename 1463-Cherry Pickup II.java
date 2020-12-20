
class CherryPickupII {

    // Top-down. Time = O(M*N^2); Space = O(M*N^2);
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return helper(0, 0, n - 1, grid, dp);
    }

    private int helper(int row, int col1, int col2, int[][] grid, int[][][] dp) {
        if (col1 < 0 || col1 >= grid[0].length || col2 < 0 || col2 >= grid[0].length) {
            return 0;
        }
        if (dp[row][col1][col2] != -1) {
            return dp[row][col1][col2];
        }
        int res = 0;
        res += grid[row][col1];
        if (col1 != col2) {
            res += grid[row][col2];
        }
        if (row != grid.length - 1) {
            int max = 0;
            for (int i = col1 - 1; i <= col1 + 1; i++) {
                for (int j = col2 - 1; j <= col2 + 1; j++) {
                    max = Math.max(max, helper(row + 1, i, j, grid, dp));
                }
            }
            res += max;
        }
        dp[row][col1][col2] = res;
        return res;
    }

    // Bottom-up.
    public int cherryPickupI(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int dp[][][] = new int[m][n][n];

        for (int row = m - 1; row >= 0; row--) {
            for (int col1 = 0; col1 < n; col1++) {
                for (int col2 = 0; col2 < n; col2++) {
                    int result = 0;
                    // current cell
                    result += grid[row][col1];
                    if (col1 != col2) {
                        result += grid[row][col2];
                    }
                    // transition
                    if (row != m - 1) {
                        int max = 0;
                        for (int newCol1 = col1 - 1; newCol1 <= col1 + 1; newCol1++) {
                            for (int newCol2 = col2 - 1; newCol2 <= col2 + 1; newCol2++) {
                                if (newCol1 >= 0 && newCol1 < n && newCol2 >= 0 && newCol2 < n) {
                                    max = Math.max(max, dp[row + 1][newCol1][newCol2]);
                                }
                            }
                        }
                        result += max;
                    }
                    dp[row][col1][col2] = result;
                }
            }
        }
        return dp[0][0][n - 1];
    }

}