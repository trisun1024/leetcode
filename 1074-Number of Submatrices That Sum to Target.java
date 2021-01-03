import java.util.*;

class NumberOfSubmatricesThatSumToTarget {

    // Horizontal 1D Prefix Sum. Time = O(R^2*C);
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        // build 2D prefix sum
        int[][] prefix = build2DPrefixSum(matrix, row, col);
        // reduce 2D problem to 1D by fixing two rows r1 and r2,
        // and compute 1D prefix sum for all matrices using [r1...r2] rows
        int count = 0;
        for (int r1 = 1; r1 <= row; r1++) {
            for (int r2 = r1; r2 <= row; r2++) {
                count += countSubmatrixToTarget(matrix, prefix, target, new HashMap<>(), r1, r2, row, col);
            }
        }
        return count;
    }

    private int countSubmatrixToTarget(int[][] matrix, int[][] prefix, int target, Map<Integer, Integer> map, int r1,
            int r2, int row, int col) {
        int count = 0;
        map.clear();
        map.put(0, 1);
        for (int c = 1; c <= col; c++) {
            int curSum = prefix[r2][c] - prefix[r1 - 1][c];
            count += map.getOrDefault(curSum - target, 0);
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        return count;
    }

    private int[][] build2DPrefixSum(int[][] matrix, int row, int col) {
        // build 2D prefix sum
        int[][] prefix = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        return prefix;
    }
}