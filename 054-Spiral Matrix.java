import java.util.*;

class SpiralMatrix {

    // Iteration.
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int row = matrix.length;
        if (row == 0) {
            return res;
        }
        int col = matrix[0].length;
        int left = 0;
        int right = col - 1;
        int up = 0;
        int down = row - 1;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }
            for (int i = up + 1; i <= down; i++) {
                res.add(matrix[i][right]);
            }
            if (left < right && up < down) {
                for (int i = right - 1; i >= left; i--) {
                    res.add(matrix[down][i]);
                }
                for (int i = down - 1; i >= up + 1; i--) {
                    res.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
}