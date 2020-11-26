class CountSquareSubmatricesWithAllOnes {

    // brute force
    public int countSquares(int[][] matrix) {
        int tot = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int maxSide = Math.min(m, n);
        for (int i = 1; i <= maxSide; i++) {
            tot += findSquares(matrix, i);
        }
        return tot;
    }

    private int findSquares(int[][] matrix, int side) {
        int squares = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (isValid(matrix, i, j, side)) {
                    squares++;
                }
            }
        }
        return squares;
    }

    private boolean isValid(int[][] matrix, int i, int j, int side) {
        for (int m = i; m < i + side; m++) {
            for (int n = j; n < j + side; n++) {
                if (m >= matrix.length || n >= matrix[0].length || matrix[m][n] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // inplace dp 
    
    public int countSquaresII(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != 0 && j != 0 && matrix[i][j] != 0) {
                    matrix[i][j] = Math.min(Math.min(matrix[i][j - 1], matrix[i - 1][j]), matrix[i - 1][j - 1]) + 1;
                }
                count += matrix[i][j];
            }
        }
        return count;
    }
}