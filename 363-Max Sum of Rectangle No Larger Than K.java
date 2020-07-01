import java.util.*;

class MaxSumRectangleNoLargerThanK {

    // TreeSet
    public int maxSumSubmatrix(int[][] matrix, int k) {
        // corner case
        int m = matrix.length;
        int n = 0;
        if (m > 0) {
            n = matrix[0].length;
        }
        // The rectangle inside the matrix must have an area > 0
        if (m * n == 0) {
            return 0;
        }
        int M = Math.max(m, n);
        int N = Math.min(m, n);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sums = new int[M];
            for (int j = i; j < N; j++) {
                TreeSet<Integer> set = new TreeSet<>();
                int curSum = 0;
                for (int v = 0; v < M; v++) {
                    sums[v] += m > n ? matrix[v][j] : matrix[j][v];
                    curSum += sums[v];
                    if (curSum <= k) {
                        ans = Math.max(ans, curSum);
                    }
                    Integer a = set.ceiling(curSum - k);
                    if (a != null) {
                        ans = Math.max(ans, curSum - i);
                    }
                    set.add(curSum);
                }
            }
        }
        return ans;
    }

    public int maxSumSubmatrixII(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, res = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int[] sum = new int[m];
            for (int c = i; c < n; ++c) {
                for (int r = 0; r < m; ++r) {
                    sum[r] += matrix[r][c];
                }

                int max = sum[0], maxSoFar = 0;
                for (int s : sum) {
                    maxSoFar = Math.max(maxSoFar + s, s);
                    max = Math.max(max, maxSoFar);
                    if (max == k)
                        return k;
                }
                if (max < k)
                    res = Math.max(res, max);
                else {
                    for (int r = 1; r < m; ++r) {
                        int currSum = 0;
                        for (int row = r; row < m; ++row) {
                            currSum += sum[row];
                            if (currSum <= k)
                                res = Math.max(res, currSum);
                        }
                    }
                }
                if (res == k)
                    return res;
            }
        }
        return res;
    }
}