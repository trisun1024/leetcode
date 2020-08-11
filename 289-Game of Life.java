class GameOfLife {

    // in-place solution. Time = O(M*N)
    public void gameOfLife(int[][] board) {
        int[] dir = { 0, -1, 1 };
        int row = board.length;
        int col = board[0].length;
        // loop to check
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int liveNeighbor = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(dir[i] == 0 && dir[j] == 0)) {
                            int nr = r + dir[i];
                            int nc = c + dir[j];
                            if (nr >= 0 && nr < row && nc >= 0 && nc < col && Math.abs(board[nr][nc]) == 1) {
                                liveNeighbor++;
                            }
                        }
                    }
                }
                // check rule 1 & 3
                if (board[r][c] == 1 && (liveNeighbor < 2 || liveNeighbor > 3)) {
                    board[r][c] = -1;
                }
                // rule 2
                if (board[r][c] == 0 && liveNeighbor == 3) {
                    board[r][c] = 2;
                }
            }
        }
        // get final
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}