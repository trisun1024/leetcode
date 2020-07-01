import java.util.*;

class DungeonGame {

    // DFS
    public int calculateMinimumHP(int[][] dungeon) {
        // base case
        if (dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0) {
            return 0;
        }
        // init length
        int m = dungeon.length;
        int n = dungeon[0].length;
        // dp store the history;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1); // set init value
        // dfs
        return dfs(dungeon, dp, 0, 0, m, n);
    }

    private int dfs(int[][] dungeon, int[][] dp, int x, int y, int m, int n) {
        // corner case
        if (x == m || y == n) {
            return Integer.MAX_VALUE / 2;
        }
        if (dp[x][y] > 0) {
            return dp[x][y];
        }
        // find right and down position
        int right = Math.max(dfs(dungeon, dp, x, y + 1, m, n) - dungeon[x][y], 1);
        int down = Math.max(dfs(dungeon, dp, x + 1, y, m, n) - dungeon[x][y], 1);
        // find the minimum
        dp[x][y] = Math.min(right, down);
        return dp[x][y];
    }

    // DP solution
    public int calculateMinimumHPII(int[][] dungeon) {
        // DP array
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        // len
        int m = dungeon.length;
        int n = dungeon[0].length;
        // init pos
        dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
        // Populate the last column
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(1, dp[i + 1][n - 1] - dungeon[i][n - 1]);
        }
        // Populate the last row
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = Math.max(1, dp[m - 1][i + 1] - dungeon[m - 1][i]);
        }
        // Populate teh rest
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        // return res
        return dp[0][0];
    }
}