class SparseMatrixMultiplication {

    // brute force
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                calculate(A, i, B, j, res);
            }
        }
        return res;
    }

    private void calculate(int[][] A, int row, int[][] B, int col, int[][] res) {
        int val = 0;
        for (int i = 0; i < A[0].length; i++) {
            val += A[row][i] * B[i][col];
        }
        res[row][col] = val;
    }
}