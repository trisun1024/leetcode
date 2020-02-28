import java.util.*;

class Solution {
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
        while (left < right && up < down) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }
            for (int i = up + 1; i <= down - 1; i++) {
                res.add(matrix[i][right]);
            }
            for (int i = right; i >= left; i--) {
                res.add(matrix[down][i]);
            }
            for (int i = down - 1; i >= up + 1; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }
        // post-processing
        if (left > right || up > down) {
            return res;
        }
        if (left == right) {
            for (int i = up; i <= down; i++) {
                res.add(matrix[i][left]);
            }
        } else {
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }
        }
        return res;
    }
}