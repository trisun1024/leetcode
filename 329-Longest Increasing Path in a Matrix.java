class Solution {

    // DFS + memorization
    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cache = new int[m][n];
        int longest = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                longest = Math.max(longest, dfs(matrix, i, j, m, n, cache));
            }
        }
        return longest;
    }

    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, m, n, cache));
            }
        }
        return ++cache[i][j];
    }

    //
    public int longestIncreasingPathII(int[][] matrix) {
        int ans = 0;
        if (matrix.length == 0) {
            return 0;
        }
        int[][] memo = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(ans, dfs(i, j, matrix, memo));
            }
        }
        return ans;
    }

    private int dfs(int i, int j, int[][] matrix, int[][] memo) {
        int ans = 0;
        if (memo[i][j] > 0) {
            // memo case
            return memo[i][j];
        }
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
                if (matrix[i][j] < matrix[x][y]) {
                    ans = Math.max(ans, dfs(x, y, matrix, memo));
                }
            }
        }
        memo[i][j] = ++ans;
        return memo[i][j];
    }
}