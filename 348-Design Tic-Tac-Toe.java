
// In this question, there is a trick to reduce the time complexity from O(N^2) to O(N) 
// idea is using two array and two int to represent the rows and cols, diag and reverse diag

class TicTacToe {

    private int[] rows;
    private int[] cols;
    private int diag;
    private int revDiag;
    private final int SIZE;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diag = 0;
        revDiag = 0;
        SIZE = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     * 
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1:
     *         Player 1 wins. 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        int move = player == 1 ? 1 : -1;
        if (Math.abs(rows[row] += move) == SIZE)
            return rows[row] > 0 ? 1 : 2;
        if (Math.abs(cols[col] += move) == SIZE)
            return cols[col] > 0 ? 1 : 2;
        if (row == col && Math.abs(diag += move) == SIZE)
            return diag > 0 ? 1 : 2;
        if (row + col == SIZE - 1 && Math.abs(revDiag += move) == SIZE)
            return revDiag > 0 ? 1 : 2;
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such: TicTacToe obj
 * = new TicTacToe(n); int param_1 = obj.move(row,col,player);
 */