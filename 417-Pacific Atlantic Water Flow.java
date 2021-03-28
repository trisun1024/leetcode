import java.util.*;

class PacificAtlanticWaterFlow {

    // DFS solution
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] pac = new boolean[row][col];
        boolean[][] atl = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            dfs(matrix, i, 0, row, col, Integer.MIN_VALUE, pac);
            dfs(matrix, i, col - 1, row, col, Integer.MIN_VALUE, atl);
        }
        for (int j = 0; j < col; j++) {
            dfs(matrix, 0, j, row, col, Integer.MIN_VALUE, pac);
            dfs(matrix, row - 1, j, row, col, Integer.MIN_VALUE, atl);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pac[i][j] && atl[i][j]) {
                    res.add(Arrays.asList(new Integer[] { i, j }));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, int x, int y, int row, int col, int height, boolean[][] visited) {
        if (x < 0 || x >= row || y < 0 || y >= col || matrix[x][y] < height || visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        for (int[] dir : DIRS) {
            dfs(matrix, x + dir[0], y + dir[1], row, col, matrix[x][y], visited);
        }
    }

    private final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // BFS solution
    public List<List<Integer>> pacificAtlanticII(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        Queue<int[]> pacQueue = new ArrayDeque<>();
        Queue<int[]> atlQueue = new ArrayDeque<>();
        boolean[][] pac = new boolean[rows][cols];
        boolean[][] atl = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) { // Vertical border
            pacQueue.offer(new int[] { i, 0 });
            atlQueue.offer(new int[] { i, cols - 1 });
            pac[i][0] = true;
            atl[i][cols - 1] = true;
        }
        for (int i = 0; i < cols; i++) { // Horizontal border
            pacQueue.offer(new int[] { 0, i });
            atlQueue.offer(new int[] { rows - 1, i });
            pac[0][i] = true;
            atl[rows - 1][i] = true;
        }
        bfs(matrix, pacQueue, pac);
        bfs(matrix, atlQueue, atl);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pac[i][j] && atl[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        int n = matrix.length, m = matrix[0].length;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : DIRS) {
                int x = cur[0] + d[0];
                int y = cur[1] + d[1];
                if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]) {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[] { x, y });
            }
        }
    }
}