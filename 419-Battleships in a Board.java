
class BattleshipsBoard {

    // One Pass. Time = O(N^2); Space = O(1);
    public int countBattleships(char[][] board) {
        if (board.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    if ((i > 0 && board[i - 1][j] == 'X') || (j > 0 && board[i][j - 1] == 'X')) {
                        continue;
                    } else {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // DFS
    int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int countBattleshipsI(char[][] board) {
        int n = board.length, m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && board[i][j] == 'X') {
                    dfs(board, i, j, n, m, visited);
                    result++;
                }
            }
        }
        return result;
    }

    public void dfs(char[][] board, int i, int j, int n, int m, boolean[][] visited) {
        if (i >= 0 && i < n && j >= 0 && j < m && !visited[i][j] && board[i][j] == 'X') {
            visited[i][j] = true;
            for (int[] dir : dirs) {
                dfs(board, i + dir[0], j + dir[1], n, m, visited);
            }
        }
    }
}