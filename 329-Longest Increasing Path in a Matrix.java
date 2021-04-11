import java.util.*;

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

    // Topological Sorting. Time = O(M*N); Space = O(M*N);
    public int longestIncreasingPathI(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        // padding the matrix with zero as boundaries
        // assuming all positive integer, otherwise use INT_MIN as boundaries
        int[][] matrix = new int[m + 2][n + 2];
        for (int i = 0; i < m; ++i) {
            System.arraycopy(grid[i], 0, matrix[i + 1], 1, n);
        }

        // calculate outdegrees
        int[][] outdegree = new int[m + 2][n + 2];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                for (int[] d : DIRS) {
                    if (matrix[i][j] < matrix[i + d[0]][j + d[1]]) {
                        outdegree[i][j]++;
                    }
                }
            }
        }

        // find leaves who have zero out degree as the initial level
        n += 2;
        m += 2;
        List<int[]> leaves = new ArrayList<>();
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                if (outdegree[i][j] == 0) {
                    leaves.add(new int[] { i, j });
                }
            }
        }

        // remove leaves level by level in topological order
        int height = 0;
        while (!leaves.isEmpty()) {
            height++;
            List<int[]> newLeaves = new ArrayList<>();
            for (int[] node : leaves) {
                for (int[] d : DIRS) {
                    int x = node[0] + d[0], y = node[1] + d[1];
                    if (matrix[node[0]][node[1]] > matrix[x][y]) {
                        if (--outdegree[x][y] == 0) {
                            newLeaves.add(new int[] { x, y });
                        }
                    }
                }
            }
            leaves = newLeaves;
        }
        return height;
    }
}