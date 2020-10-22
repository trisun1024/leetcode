class LongestLineOfConsecutiveOneInMatrix {

    // Brute Force
    // Time O(N^2) Space O(1)
    public int longestLine(int[][] M) {
        if (M.length == 0)
            return 0;
        int ones = 0;
        // horizontal
        for (int i = 0; i < M.length; i++) {
            int count = 0;
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        // vertical
        for (int i = 0; i < M[0].length; i++) {
            int count = 0;
            for (int j = 0; j < M.length; j++) {
                if (M[j][i] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        // upper diagonal
        for (int i = 0; i < M[0].length || i < M.length; i++) {
            int count = 0;
            for (int x = 0, y = i; x < M.length && y < M[0].length; x++, y++) {
                if (M[x][y] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        // lower diagonal
        for (int i = 0; i < M[0].length || i < M.length; i++) {
            int count = 0;
            for (int x = i, y = 0; x < M.length && y < M[0].length; x++, y++) {
                if (M[x][y] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        // upper anti-diagonal
        for (int i = 0; i < M[0].length || i < M.length; i++) {
            int count = 0;
            for (int x = 0, y = M[0].length - i - 1; x < M.length && y >= 0; x++, y--) {
                if (M[x][y] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        // lower anti-diagonal
        for (int i = 0; i < M[0].length || i < M.length; i++) {
            int count = 0;
            for (int x = i, y = M[0].length - 1; x < M.length && y >= 0; x++, y--) {
                // System.out.println(x+" "+y);
                if (M[x][y] == 1) {
                    count++;
                    ones = Math.max(ones, count);
                } else
                    count = 0;
            }
        }
        return ones;
    }

    // DP 3D. Time = O(M*N); Space = O(M*N);
    public int longestLineI(int[][] M) {
        if (M.length == 0) {
            return 0;
        }
        int ones = 0;
        int[][][] dp = new int[M.length][M[0].length][4];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    dp[i][j][0] = j > 0 ? dp[i][j - 1][0] + 1 : 1;
                    dp[i][j][1] = i > 0 ? dp[i - 1][j][1] + 1 : 1;
                    dp[i][j][2] = (i > 0 && j > 0) ? dp[i - 1][j - 1][2] + 1 : 1;
                    dp[i][j][3] = (i > 0 && j < M[0].length - 1) ? dp[i - 1][j + 1][3] + 1 : 1;
                    ones = Math.max(ones,
                            Math.max(Math.max(dp[i][j][0], dp[i][j][1]), Math.max(dp[i][j][2], dp[i][j][3])));
                }
            }
        }
        return ones;
    }

    // DP 2D. Time = O(M*N); Space = O(N);
    public int longestLineII(int[][] M) {
        if (M.length == 0 || M[0].length == 0) {
            return 0;
        }
        int rows = M.length;
        int cols = M[0].length;
        // DP Arrays. 0 - horizontal; 1 - vertical; 2 - diagonal; 3 - reverse diagonal;
        int[][] dp = new int[4][cols];

        int res = 0;
        for (int i = 0; i < rows; i++) {

            int prev = 0;
            for (int j = 0; j < cols; j++) {

                if (M[i][j] == 0) {
                    prev = dp[2][j];
                    dp[0][j] = dp[1][j] = dp[2][j] = dp[3][j] = 0;
                    continue;
                }

                // horizontal
                dp[0][j] = 1 + (j > 0 ? dp[0][j - 1] : 0);

                // vertical
                dp[1][j] = 1 + (i > 0 ? dp[1][j] : 0);

                // diagnal
                int old = dp[2][j];
                dp[2][j] = 1 + (i > 0 && j > 0 ? prev : 0);
                prev = old; 

                // counter diagonal
                dp[3][j] = 1 + (i > 0 && j + 1 < M[i].length ? dp[3][j + 1] : 0);

                for (int k = 0; k < 4; k++) {
                    if (res < dp[k][j]) {
                        res = dp[k][j];
                    }
                }
            }
        }

        return res;
    }

    // DFS
    public int longestLineIV(int[][] M) {
        if (M == null) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    res = Math.max(res, getMaxOneLine(M, i, j));
                }
            }
        }
        return res;
    }

    final int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { 1, 1 }, { 1, -1 } };

    private int getMaxOneLine(int[][] M, int x, int y) {
        int res = 1;
        for (int[] dir : dirs) {
            int i = x + dir[0];
            int j = y + dir[1];
            int count = 1;
            while (isValidPosition(M, i, j) && M[i][j] == 1) {
                i += dir[0];
                j += dir[1];
                count++;
            }
            res = Math.max(count, res);
        }
        return res;
    }

    private boolean isValidPosition(int M[][], int i, int j) {
        return (i < M.length && j < M[0].length && i >= 0 && j >= 0);
    }
}