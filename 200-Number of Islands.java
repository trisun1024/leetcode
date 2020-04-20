import java.util.*;

// DFS time O(m*n) space O(m*n)
class SolutionDFS {
    // assumption:
    final static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int numIslands(char[][] grid) {
        // sanity check
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int count = 0;
        final int rows = grid.length;
        final int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j, rows, cols);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int x, int y, int rows, int cols) {
        // base case
        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == '0') {
            return;
        }
        // recursive case
        grid[x][y] = '0';
        for (int[] dir : dirs) {
            int innerX = dir[0] + x;
            int innerY = dir[1] + y;
            dfs(grid, innerX, innerY, rows, cols);
        }
    }

    // BFS time O(m*n) space O(min(m,n))
    public int numIslandsII(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0;
        final int rows = grid.length;
        final int cols = grid[0].length;

        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (grid[r][c] == '1') {
                    ++count;
                    // if require grid unchanged, marked as other;
                    // or marked as '0' as visited
                    grid[r][c] = '0'; // mark as visited
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * cols + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / cols;
                        int col = id % cols;
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbors.add((row - 1) * cols + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < rows && grid[row + 1][col] == '1') {
                            neighbors.add((row + 1) * cols + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbors.add(row * cols + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < cols && grid[row][col + 1] == '1') {
                            neighbors.add(row * cols + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }

        return count;
    }
}
