class Solution {

    // DFS
    public boolean exist(char[][] board, String word) {
        // corner case
        if (board == null || word == null || word.length() == 0) {
            return false;
        }
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final int[][] DIRS = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private boolean dfs(char[][] board, int i, int j, String word, int index, boolean[][] visited) {
        if (index >= word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)
                || visited[i][j]) {
            return false;
        }
        // expand four directions
        visited[i][j] = true;
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (dfs(board, x, y, word, index + 1, visited)) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }
}