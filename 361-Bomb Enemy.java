import java.util.*;

class BombEnemy {

    // Two Pointers. Time = O(M*N*(M+N));
    public int maxKilledEnemies(char[][] grid) {
        int max = 0;
        if (grid.length == 0) {
            return max;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '0') {
                    max = Math.max(max, bomb(grid, r, c));
                }
            }
        }
        return max;
    }

    private int bomb(char[][] grid, int row, int col) {
        int kill = 0;
        int l = col;
        int r = col;
        while (l >= 0 && grid[row][l] != 'W') {
            if (grid[row][l] == 'E') {
                kill++;
            }
            l--;
        }
        while (r < grid[0].length && grid[row][r] != 'W') {
            if (grid[row][r] == 'E') {
                kill++;
            }
            r++;
        }
        int u = row;
        int d = row;
        while (u >= 0 && grid[u][col] != 'W') {
            if (grid[u][col] == 'E') {
                kill++;
            }
            u--;
        }
        while (d < grid.length && grid[d][col] != 'W') {
            if (grid[d][col] == 'E') {
                kill++;
            }
            d++;
        }
        return kill;
    }

    //
    public int maxKilledEnemiesI(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int maxKill = 0;
        int[][] kills = new int[m][n];
        int[] enemies = new int[n];
        for (int i = 0; i < m; i++) {
            int enemy = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'E') {
                    enemy++;
                    enemies[j]++;
                } else if (grid[i][j] == 'W') {
                    enemy = 0;
                    enemies[j] = 0;
                } else {
                    kills[i][j] = enemy + enemies[j];
                }
            }
        }

        Arrays.fill(enemies, 0);
        for (int i = m - 1; i >= 0; i--) {
            int enemy = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 'E') {
                    enemy++;
                    enemies[j]++;
                } else if (grid[i][j] == 'W') {
                    enemy = 0;
                    enemies[j] = 0;
                } else {
                    kills[i][j] += enemy + enemies[j];
                    maxKill = Math.max(maxKill, kills[i][j]);
                }
            }
        }

        return maxKill;
    }
}