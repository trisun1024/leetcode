import java.util.*;

class BinaryMatrix {

    private final int[][] DIRS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    // Queue.
    public int[][] updateMatrix(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    explore(matrix, i, j, new boolean[m][n]);
                }
            }
        }
        return matrix;
    }

    private void explore(int[][] matrix, int startX, int startY, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { startX, startY });
        visited[startX][startY] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (matrix[cur[0]][cur[1]] == 0) {
                    matrix[startX][startY] = steps;
                    return;
                }
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && !visited[x][y]) {
                        queue.offer(new int[] { x, y });
                    }
                }
            }
            steps++;
        }
    }

    // Queue Store Zero.
    public int[][] updateMatrixI(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] { i, j });
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : DIRS) {
                int r = cur[0] + dir[0];
                int c = cur[1] + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n || matrix[r][c] <= matrix[cur[0]][cur[1]] + 1) {
                    continue;
                }
                queue.offer(new int[] { r, c });
                matrix[r][c] = matrix[cur[0]][cur[1]] + 1;
            }
        }

        return matrix;
    }

    public int[][] updateMatrixII(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE - 1);
        }
        // First pass
        // Check top and left neighbor
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                }
                // Check top neighbor if not edge
                if (i > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
                // Check left neighbor if not edge
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        }

        // Second pass
        // Check bottom and right
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                // Check bot neighbor if not edge
                if (i < matrix.length - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                }
                // Check right neighbor if not edge
                if (j < matrix[0].length - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                }

            }
        }
        return dp;
    }

}