import java.util.*;

class Solution {

    // DP Time = O(N^2*M); Space = O(N*M)
    public int maximalRectangleII(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int area = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
                    int width = dp[i][j];
                    for (int k = i; k >= 0; k--) {
                        width = Math.min(width, dp[k][j]);
                        area = Math.max(area, width * (i - k + 1));
                    }
                }
            }
        }
        return area;
    }

    // DP Maximum Height at Each Point. Time = O(N*M); Space = O(M);
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n];
        int area = 0;
        for (int i = 0; i < m; i++) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (int j = 0; j <= n; j++) {
                if (j < n) {
                    dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
                }
                int value = j == n ? 0 : dp[j];
                while (!stack.isEmpty() && value < dp[stack.peekFirst()]) {
                    int index = stack.pollFirst();
                    int height = dp[index];
                    int width = stack.isEmpty() ? j : j - stack.peekFirst() - 1;
                    area = Math.max(area, height * width);
                }
                stack.offerFirst(j);
            }
        }
        return area;
    }

    //
    public int maximalRectangleI(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length, n = matrix[0].length, result = 0;
        int[] height = new int[n], left = new int[n], right = new int[n];
        Arrays.fill(right, n);

        for (int i = 0; i < m; i++) {
            int currLeft = 0, currRight = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }

            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], currLeft);
                } else {
                    left[j] = 0;
                    currLeft = j + 1;
                }
            }

            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], currRight);
                } else {
                    right[j] = n;
                    currRight = j;
                }
            }

            for (int j = 0; j < n; j++) {
                result = Math.max(result, (right[j] - left[j]) * height[j]);
            }
        }

        return result;
    }
}