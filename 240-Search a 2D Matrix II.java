class Search2DMatrixII {

    // Reduce search space. Time = O(N+M); Space = O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
    }

    // Binary Search. Time = O(M*log(N));
    public boolean searchMatrixI(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            int left = 0;
            int right = matrix[0].length;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (target < matrix[i][mid])
                    right = mid;
                else if (target > matrix[i][mid])
                    left = mid + 1;
                else
                    return true;
            }
        }
        return false;
    }
}