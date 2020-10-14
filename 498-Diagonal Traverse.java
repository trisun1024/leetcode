import java.util.*;

class DiagonalTraverse {

    // Iteration.
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] res = new int[rows * cols];
        int k = 0;
        List<Integer> intermediate = new ArrayList<>();
        for (int i = 0; i < rows + cols - 1; i++) {
            intermediate.clear();
            int r = i < cols ? 0 : i - cols + 1;
            int c = i < cols ? i : cols - 1;
            while (r < rows && c > -1) {
                intermediate.add(matrix[r][c]);
                r++;
                c--;
            }
            if (i % 2 == 0) {
                Collections.reverse(intermediate);
            }
            for (int j = 0; j < intermediate.size(); j++) {
                res[k++] = intermediate.get(j);
            }
        }
        return res;
    }

    //
    public int[] findDiagonalOrderI(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] res = new int[rows * cols];
        res[0] = matrix[0][0];
        int i = 0;
        int j = 0;
        int k = 1;
        while (k < rows * cols) {
            // move up-right first
            while (i >= 1 && j < cols - 1) {
                i--;
                j++;
                res[k++] = matrix[i][j];
            }
            // when we can't move up-right ,then move right one step
            if (j < cols - 1) {
                j++;
                res[k++] = matrix[i][j];
            } else if (i < rows - 1) {
                // if we can't move right,just move down one step
                i++;
                res[k++] = matrix[i][j];
            }
            // After that,we will move down-left until it can't move
            while (i < rows - 1 && j >= 1) {
                i++;
                j--;
                res[k++] = matrix[i][j];
            }
            // if we can't move down-left,then move down
            if (i < rows - 1) {
                i++;
                res[k++] = matrix[i][j];
            } else if (j < cols - 1) {
                // if we can't move down,just move right
                j++;
                res[k++] = matrix[i][j];
            }
        }
        return res;
    }
}