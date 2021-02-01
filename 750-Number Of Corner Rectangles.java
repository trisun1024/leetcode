
class NumberOfCornerRectangles {

    // Math. Time = O(M^2*N);
    public int countCornerRectangles(int[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                // count how many pairs of vertical points exists in each row combination
                int c = 0;
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] == 1 && grid[j][k] == 1) {
                        c++;
                    }
                }
                // numbers > 0 then do the math
                if (c > 0) {
                    count += c * (c - 1) / 2;
                }
            }
        }
        return count;
    }

    // DP.
    public int countCornerRectanglesI(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    if (grid[i][k] == 0) {
                        continue;
                    }
                    res += dp[j][k];
                    dp[j][k]++;
                }
            }
        }
        return res;
    }
}