class LongestIncreasingPathInMatrix {

    // DFS + memorization. Time = O(M*N); Space = O(M*N);
    private static final int[][] DIRS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int longestIncreasingPath(int[][] matrix) {
        int ans = 0;
        if (matrix.length == 0) {
            return ans;
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
        if (memo[i][j] > 0) {
            // memo case
            return memo[i][j];
        }
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[i][j] < matrix[x][y]) {
                memo[i][j] = Math.max(memo[i][j], dfs(x, y, matrix, memo));
            }
        }
        return ++memo[i][j];
    }
}