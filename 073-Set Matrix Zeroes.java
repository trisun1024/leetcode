import java.util.*;

class Solution {

    // solution 1
    // use the whole matrix to save the row or col require a change
    // Time O(M*N) Space O(M*N)

    // solution 2
    // use boolean array to save the row or col need change to zero
    // Time O(M*N) Space O(M+N)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0 && n == 0) {
            return;
        }
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (row[i]) {
                setAllRow(matrix, i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (col[i]) {
                setAllCol(matrix, i);
            }
        }
    }

    private void setAllRow(int[][] matrix, int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] = 0;
        }
    }

    private void setAllCol(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }

    // solution 3
    // use another value to store the location required a change
    // Time O(M*N) Space O(1)
    public void setZeroesII(int[][] matrix) {
        int MODIFIED = -1000000; // assume a number is not in the matrix, set to this number when find it
        int R = matrix.length;
        int C = matrix[0].length;
        // find zero and set them to modified value
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (matrix[r][c] == 0) {
                    for (int k = 0; k < C; k++) {
                        if (matrix[r][k] != 0) {
                            matrix[r][k] = MODIFIED;
                        }
                    }
                    for (int k = 0; k < R; k++) {
                        if (matrix[k][c] != 0) {
                            matrix[k][c] = MODIFIED;
                        }
                    }
                }
            }
        }
        // change location to zero if value is modified value
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (matrix[r][c] == MODIFIED) {
                    matrix[r][c] = 0;
                }
            }
        }
    }
}