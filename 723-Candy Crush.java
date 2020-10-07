class CandyCrush {

    public int[][] candyCrush(int[][] board) {
        boolean flag = false;
        int row = board.length;
        int col = board[0].length;
        // explore horizontal, find consetive three
        for (int i = 0; i < row; i++) {
            for (int j = 0; j + 2 < col; j++) {
                int v = Math.abs(board[i][j]);
                if (v != 0 && v == Math.abs(board[i][j + 1]) && v == Math.abs(board[i][j + 2])) {
                    board[i][j] = board[i][j + 1] = board[i][j + 2] = -v;
                    flag = true;
                }
            }
        }
        // explore vertical, find consective three
        for (int i = 0; i + 2 < row; i++) {
            for (int j = 0; j < col; j++) {
                int v = Math.abs(board[i][j]);
                if (v != 0 && v == Math.abs(board[i + 1][j]) && v == Math.abs(board[i + 2][j])) {
                    board[i][j] = board[i + 1][j] = board[i + 2][j] = -v;
                    flag = true;
                }
            }
        }
        // set all the negative value to zero, and move
        for (int j = 0; j < col; j++) {
            int bottom = row - 1;
            for (int i = row - 1; i >= 0; i--) {
                if (board[i][j] > 0) {
                    board[bottom--][j] = board[i][j];
                }
            }
            while (bottom >= 0) {
                board[bottom--][j] = 0;
            }
        }
        return flag ? candyCrush(board) : board;
    }
}