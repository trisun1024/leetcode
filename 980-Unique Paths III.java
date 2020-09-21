class UniquePathsIII {

    // DFS. Time = O(3^N);
    public int uniquePathsIII(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // init
        int obstacle = 0;
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int v = grid[i][j];
                if (v < 0) {
                    obstacle++;
                } else if (v == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }
        // count init
        int[] pathCount = new int[] { 0 };
        dfs(grid, startX, startY, rows * cols - obstacle, pathCount, rows, cols);
        return pathCount[0];
    }

    private final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    private void dfs(int[][] grid, int i, int j, int remain, int[] pathCount, int rows, int cols) {
        if (grid[i][j] == 2 && remain == 1) {
            pathCount[0]++;
            return;
        }
        int val = grid[i][j];
        grid[i][j] = -3;
        remain--;
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] >= 0) {
                dfs(grid, x, y, remain, pathCount, rows, cols);
            }
        }
        grid[i][j] = val;
    }
}