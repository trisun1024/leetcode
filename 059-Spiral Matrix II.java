class SpiralMatrixII {

    // Level by Level.
    public int[][] generateMatrixI(int n) {
        int[][] res = new int[n][n];
        if (n == 0) {
            return res;
        }
        int val = 1;
        for (int layer = 0; layer < (n + 1) / 2; layer++) {
            // direction 1 - traverse from left to right
            for (int i = layer; i < n - layer; i++) {
                res[layer][i] = val++;
            }
            // direction 2 - traverse from top to bottom
            for (int i = layer + 1; i < n - layer; i++) {
                res[i][n - layer - 1] = val++;
            }
            // direction 3 - traverse from right to left
            for (int i = layer + 1; i < n - layer; i++) {
                res[n - layer - 1][n - i - 1] = val++;
            }
            // direction 4 - traverse from bottom to top
            for (int i = layer + 1; i < n - layer - 1; i++) {
                res[n - i - 1][layer] = val++;
            }
        }
        return res;
    }

    // Recursion.
    public int[][] generateMatrixII(int n) {
        int[][] matrix = new int[n][n];
        if (n == 0) {
            return matrix;
        }
        helper(matrix, 0, n, 1);
        return matrix;
    }

    private void helper(int[][] matrix, int offset, int size, int num) {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            matrix[offset][offset] = num;
            return;
        }
        for (int i = 0; i < size - 1; i++) {
            matrix[offset][offset + i] = num++;
        }
        for (int i = 0; i < size - 1; i++) {
            matrix[offset + i][offset + size - 1] = num++;
        }
        for (int i = size - 1; i > 0; i--) {
            matrix[offset + size - 1][offset + i] = num++;
        }
        for (int i = size - 1; i > 0; i--) {
            matrix[offset + i][offset] = num++;
        }
        helper(matrix, offset + 1, size - 2, num);
    }

    // Iteration.
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n == 0) {
            return res;
        }
        int left = 0;
        int right = n - 1;
        int up = 0;
        int down = n - 1;
        int num = 1;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                res[up][i] = num++;
            }
            for (int i = up + 1; i <= down; i++) {
                res[i][right] = num++;
            }
            for (int i = right - 1; i >= left; i--) {
                res[down][i] = num++;
            }
            for (int i = down - 1; i > up; i--) {
                res[i][left] = num++;
            }
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
}