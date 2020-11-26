class largest1BorderedSquare {

    // DP. Time = O(N^2)
    public int largest1BorderedSquare(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int N = grid.length;
        int M = grid[0].length;
        int res = 0;
        int[][] left = new int[N + 1][M + 1];
        int[][] up = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    left[i + 1][j + 1] = left[i + 1][j] + 1;
                    up[i + 1][j + 1] = up[i][j + 1] + 1;
                    for (int len = Math.min(left[i + 1][j + 1], up[i + 1][j + 1]); len >= 1; len--) {
                        if (left[i + 2 - len][j + 1] >= len && up[i + 1][j + 2 - len] >= len) {
                            res = Math.max(res, len);
                            break;
                        }
                    }
                }
            }
        }
        return res * res;
    }

    // separation
    public int largest1BorderedSquareII(int[][] grid) {
        int max = 0;
        int r = grid.length;
        int c = grid[0].length;
        int hor[][] = new int[r][c];
        int ver[][] = new int[r][c];
        hor[0][0] = ver[0][0] = 1;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 0)
                    ver[i][j] = hor[i][j] = 0;
                else {
                    hor[i][j] = (j == 0) ? 1 : hor[i][j - 1] + 1;
                    ver[i][j] = (i == 0) ? 1 : ver[i - 1][j] + 1;
                    max = 1;
                }
            }
        }

        for (int i = r - 1; i >= 1; i--) {
            for (int j = c - 1; j >= 1; j--) {
                int small = Math.min(hor[i][j], ver[i][j]);

                while (small > max) {
                    if (ver[i][j - small + 1] >= small && hor[i - small + 1][j] >= small) {
                        max = small;
                    }
                    small--;
                }
            }
        }
        return max * max;
    }
}