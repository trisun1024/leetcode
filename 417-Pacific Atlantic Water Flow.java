import java.util.*;

class Solution {

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

    private final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    private void dfs(int[][] matrix, int x, int y, int row, int col, int height, boolean[][] visited) {
        if (x < 0 || x >= row || y < 0 || y >= col || matrix[x][y] < height || visited[x][y]) {
            return;
        }
        visited[x][y] = true;
        for (int[] dir : DIRS) {
            dfs(matrix, x + dir[0], y + dir[1], row, col, matrix[x][y], visited);
        }
    }

    // BFS solution
    public List<List<Integer>> pacificAtlanticII(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        return res;
    }
}