class MaximalSquare {

    // Brute Force
    public int maximalSquareI(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int sqlLen = 1;
                    boolean flag = true;
                    while (sqlLen + i < m && sqlLen + j < n && flag) {
                        for (int k = j; k <= sqlLen + j; k++) {
                            if (matrix[i + sqlLen][k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        for (int k = i; k <= sqlLen + i; k++) {
                            if (matrix[i + sqlLen][k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            sqlLen++;
                        }
                    }
                    if (max < sqlLen) {
                        max = sqlLen;
                    }
                }
            }
        }
        return max * max;
    }

    // DP T = O(M*N) S = O(M*N)
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int max = 0;
        int[][] M = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    M[i][j] = 1 + Math.min(M[i - 1][j - 1], Math.min(M[i - 1][j], M[i][j - 1]));
                    max = Math.max(max, M[i][j]);
                }
            }
        }
        return max * max;
    }

    // DP T = O(M*N) S = O(min(M,N))
    public int maximalSquareII(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int max = 0;
        int prev = 0;
        int[] M = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int tmp = M[j];
                if (matrix[i - 1][j - 1] == '1') {
                    M[j] = Math.min(M[j], Math.min(M[j - 1], prev)) + 1;
                    max = Math.max(max, M[j]);
                } else {
                    M[j] = 0;
                }
                prev = tmp;
            }
        }
        return max * max;
    }
}