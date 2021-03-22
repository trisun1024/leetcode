import java.util.*;

class NumberOfClosedIslands {

    // DFS.
    public int closedIsland(int[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[0].length; ++j)
                if (grid[i][j] == 0)
                    cnt += dfs(i, j, grid);
        return cnt;
    }

    private int dfs(int i, int j, int[][] g) {
        if (i < 0 || i >= g.length || j < 0 || j >= g[0].length)
            return 0;
        if (g[i][j] > 0)
            return 1;
        g[i][j] = 2;
        return dfs(i + 1, j, g) * dfs(i - 1, j, g) * dfs(i, j + 1, g) * dfs(i, j - 1, g);
    }

    // BFS.
    public int closedIslandI(int[][] grid) {
        int cnt = 0;
        int m = grid.length;
        int n = m == 0 ? 0 : grid[0].length;
        Set<Integer> seenLand = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 && seenLand.add(i * n + j)) { // (i, j) is land never seen before.
                    cnt += bfs(i, j, grid, seenLand, m, n);
                }
            }
        }
        return cnt;
    }

    private int bfs(int i, int j, int[][] g, Set<Integer> seenLand, int m, int n) {
        int ans = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(i * n + j);
        while (!q.isEmpty()) {
            i = q.peek() / n;
            j = q.poll() % n;
            for (int[] dir : DIRS) {
                int r = i + dir[0];
                int c = j + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n) { // out of boundary.
                    ans = 0; // set 0;
                } else if (g[r][c] == 0 && seenLand.add(r * n + c)) { // (r, c) is land never seen before.
                    q.offer(r * n + c);
                }
            }
        }
        return ans;
    }

    private final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // Union Find.
    private static final int[] d = { 0, 1, 0, -1, 0 };
    private int m, n; // numbers of rows and comlumns of grid.
    private int[] id; // parent ids.

    public int closedIslandII(int[][] grid) {
        m = grid.length;
        n = m == 0 ? 0 : grid[0].length;
        id = new int[m * n]; // Initialized as i * n + j the parent id of cell (i, j).
        for (int i = 0; i < m * n; i++) {
            id[i] = i;
        }
        for (int i = 1; i < m - 1; ++i) // traverse non-boundary rows.
            for (int j = 1; j < n - 1; ++j) // traverse non-boundary cells within a row.
                if (grid[i][j] == 0) // (i, j) is land.
                    for (int[] dir : DIRS) {
                        int r = i + dir[0];
                        int c = j + dir[1];
                        if (grid[r][c] == 0) {// (r, c) is a land neighbor.
                            union(i * n + j, r * n + c);
                        }
                    }
        int cnt = 0; // number of closed islands: number of the non-boundary lands that are ids
                     // (parent) of itself.
        for (int i = 1; i < m - 1; ++i) // traverse non-boundary rows.
            for (int j = 1; j < n - 1; ++j) // traverse non-boundary cells within a row.
                if (grid[i][j] == 0 && id[i * n + j] == i * n + j) // Is (i, j) a land as well as the id (parent) of
                                                                   // self?
                    ++cnt;
        return cnt;
    }

    private int find(int x) {
        while (x != id[x])
            x = id[x];
        return x;
    }

    private void union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY)
            return;
        if (isBoundary(rootY)) {
            id[rootX] = rootY;
        } else {
            id[rootY] = rootX;
        }
    }

    private boolean isBoundary(int id) {
        int i = id / n, j = id % n;
        return i == 0 || j == 0 || i == m - 1 || j == n - 1;
    }
}