import java.util.*;

class PathWithMaximumGold {

    public static final int[][] DIRS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    // DFS. Time = O(N)
    public int getMaximumGold(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dfs(grid, i, j, new boolean[m][n]));
            }
        }
        return result;
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int origin = grid[i][j];
        grid[i][j] = 0; // mark visited.
        int max = 0;
        for (int[] dir : DIRS) { // traverse 4 neighbors to get max value.
            max = Math.max(max, dfs(grid, i + dir[0], j + dir[1], visited));
        }
        grid[i][j] = origin; // change back.
        return max + origin;
    }

    // BFS.
    public int getMaximumGoldII(int[][] grid) {
        int ans = 0, m = grid.length, n = grid[0].length;
        int[][] oneCellTrace = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0, goldCellId = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > 0) {
                    oneCellTrace[i][j] = 1 << goldCellId++;
                    q.offer(new int[] { i, j, grid[i][j], oneCellTrace[i][j] });
                }
            }
        }
        while (!q.isEmpty()) {
            int i = q.peek()[0], j = q.peek()[1], sum = q.peek()[2], trace = q.poll()[3];
            ans = Math.max(sum, ans);
            for (int[] dir : DIRS) {
                int r = i + dir[0], c = j + dir[1];
                if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] > 0 && (trace & oneCellTrace[r][c]) == 0) {
                    q.offer(new int[] { r, c, sum + grid[r][c], trace | oneCellTrace[r][c] });
                }
            }
        }
        return ans;
    }
}