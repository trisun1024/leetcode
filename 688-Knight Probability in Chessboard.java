import java.util.*;

class KnightProbabilityInChessboard {

    // BFS
    public double knightProbabilityII(int N, int K, int r, int c) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(r, c));
        int count = 0;
        int level = 0;
        while (!queue.isEmpty()) {
            ++level;
            int size = queue.size();
            if (level == K) {
                count = size;
            }
            while (size > 0) {
                size--;
                Point cur = queue.poll();
                for (int[] dir : DIRS) {
                    int x = cur.x + dir[0];
                    int y = cur.y + dir[1];
                    if (x >= 0 && x < N && y >= 0 && y < N) {
                        queue.offer(new Point(x, y));
                    }
                }
            }
        }
        return (double) count * 1.0 / Math.pow(8, K);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final int[][] DIRS = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 },
            { -1, -2 } };

    // Recursion + DP
    public double knightProbability(int N, int K, int r, int c) {
        return helper(N, K, r, c, 1, new double[N][N][K + 1]);
    }

    private double helper(int N, int K, int r, int c, double p, double[][][] dp) {
        if (r < 0 || r >= N || c < 0 || c >= N) {
            return 0;
        }
        if (K == 0) {
            return p;
        }
        if (dp[r][c][K] != 0) {
            return dp[r][c][K];
        }
        double prob = 0;
        for (int[] dir : DIRS) {
            int x = r + dir[0];
            int y = c + dir[1];
            prob += helper(N, K - 1, x, y, p / 8, dp);
        }
        dp[r][c][K] = prob;
        return prob;
    }

    // Iteration
    public double knightProbabilityI(int N, int K, int r, int c) {
        double[][] cur = new double[N][N];
        double[][] next = new double[N][N];
        // init
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cur[i][j] = 0;
                next[i][j] = 0;
            }
        }
        // starting point
        cur[r][c] = 1;
        while (K > 0) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cur[i][j] > 0) {
                        for (int[] dir : DIRS) {
                            int x = i + dir[0];
                            int y = j + dir[1];
                            if (x >= 0 && x < N && y >= 0 && y < N) {
                                next[x][y] = next[x][y] == 0 ? 0.125 * cur[i][j] : next[x][y] + (0.125 * cur[i][j]);
                            }
                        }
                        cur[i][j] = 0;
                    }
                }
            }
            K--;
            cur = next;
            next = new double[N][N];
        }
        // get final res
        double prob = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                prob += cur[i][j];
            }
        }
        return prob;
    }
}