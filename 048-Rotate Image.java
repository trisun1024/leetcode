// time: O(n^2) space: O(n)
class RotateImage {

    // Sol1.
    public void rotate(int[][] matrix) {
        if (matrix.length != matrix[0].length)
            return;
        int n = matrix.length;
        // transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        // reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }

    // Rotate As Rectangle.
    public void rotateI(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2 + n % 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int[] tmp = new int[4];
                int row = i;
                int col = j;
                for (int k = 0; k < 4; k++) {
                    tmp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                for (int k = 0; k < 4; k++) {
                    matrix[row][col] = tmp[(k + 3) % 4];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
            }
        }
    }

    // Rotate In A Single Loop.
    public void rotateII(int[][] matrix) {
        int n = matrix.length;
        for (int r = 0; r < n / 2; r++) {
            for (int c = 0; c < (n + 1) / 2; c++) {
                int temp = matrix[n - 1 - r][c];
                matrix[n - 1 - r][c] = matrix[n - 1 - c][n - 1 - r];
                matrix[n - 1 - c][n - 1 - r] = matrix[r][n - 1 - c];
                matrix[r][n - 1 - c] = matrix[c][r];
                matrix[c][r] = temp;
            }
        }
    }
}
