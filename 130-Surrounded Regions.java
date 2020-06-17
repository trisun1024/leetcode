import java.util.*;

class Solution {

    // DFS solution
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            }
            if (board[board.length - 1][i] == 'O') {
                dfs(board, board.length - 1, i);
            }

        }
        for (int i = 1; i < board.length; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][board[0].length - 1] == 'O') {
                dfs(board, i, board[0].length - 1);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'v') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'v';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }

    // BFS
    int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public void solveII(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                surrounded(i, 0, board);
            }
            if (board[i][cols - 1] == 'O') {
                surrounded(i, cols - 1, board);
            }
        }
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O') {
                surrounded(0, i, board);
            }
            if (board[rows - 1][i] == 'O') {
                surrounded(rows - 1, i, board);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void surrounded(int x, int y, char[][] board) {
        Queue<Integer> q = new ArrayDeque<>();
        int rows = board.length;
        int cols = board[0].length;
        q.offer(x * cols + y);
        board[x][y] = 'T';
        while (!q.isEmpty()) {
            int curr = q.poll();
            int i = curr / cols;
            int j = curr % cols;
            for (int[] dir : dirs) {
                int dirX = dir[0] + i;
                int dirY = dir[1] + j;
                if (dirX >= 0 && dirX < rows && dirY >= 0 && dirY < cols && board[dirX][dirY] == 'O') {
                    board[dirX][dirY] = 'T';
                    q.offer(dirX * cols + dirY);
                }
            }
        }
    }
}