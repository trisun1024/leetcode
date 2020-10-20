import java.util.*;

class Minesweeper {

    private final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 },
            { 1, -1 } };

    // BFS.
    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0];
        int c = click[1];
        // base case
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Deque<Integer> queue = new ArrayDeque<>();

        visited[r][c] = true;
        int adjMine = adjacentMines(r, c, board, visited, rows, cols);
        if (adjMine == 0) {
            board[r][c] = 'B';
            queue.offer(r * cols + c);
        } else {
            board[r][c] = (char) (adjMine + '0');
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                int x = index / cols;
                int y = index % cols;
                if (Character.isDigit(board[x][y])) {
                    continue;
                }
                for (int[] dir : DIRS) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || visited[nx][ny] || board[nx][ny] == 'M'
                            || Character.isDigit(board[nx][ny])) {
                        continue;
                    }
                    int totalMines = adjacentMines(nx, ny, board, visited, rows, cols);
                    if (totalMines == 0) {
                        queue.offer(nx * cols + ny);
                        board[nx][ny] = 'B';
                        visited[nx][ny] = true;
                    } else {
                        board[nx][ny] = (char) (totalMines + '0');
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return board;
    }

    private int adjacentMines(int x, int y, char[][] board, boolean[][] visited, int rows, int cols) {
        int numberOfMines = 0;
        for (int[] dir : DIRS) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && board[nx][ny] == 'M' && !visited[nx][ny]) {
                numberOfMines++;
            }
        }
        return numberOfMines;
    }

    // DFS.
    public char[][] updateBoardI(char[][] board, int[] click) {

        if (board == null) {
            return board;
        }

        int x = click[0];
        int y = click[1];

        if (board[x][y] == 'X' || board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        int num = 0;

        for (int[] dir : DIRS) {
            int n_x = x + dir[0];
            int n_y = y + dir[1];

            if (n_x >= 0 && n_x < board.length && n_y >= 0 && n_y < board[0].length && board[n_x][n_y] == 'M') {
                num++;
            }
        }

        if (num > 0) {
            board[x][y] = (char) (num + '0');
            return board;
        }

        board[x][y] = 'B';

        for (int[] dir : DIRS) {
            int n_x = x + dir[0];
            int n_y = y + dir[1];

            if (n_x >= 0 && n_y >= 0 && n_x < board.length && n_y < board[0].length && board[n_x][n_y] == 'E') {
                updateBoard(board, new int[] { n_x, n_y });
            }
        }

        return board;

    }
}