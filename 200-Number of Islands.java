import java.util.*;

class NumberOfIslands {

    private final static int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    // DFS time O(m*n) space O(m*n)
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j, rows, cols);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int x, int y, int rows, int cols) {
        // base case
        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == '0') {
            return;
        }
        // recursive case
        grid[x][y] = '0';
        for (int[] dir : DIRS) {
            int innerX = dir[0] + x;
            int innerY = dir[1] + y;
            dfs(grid, innerX, innerY, rows, cols);
        }
    }

    // BFS time O(m*n) space O(min(m,n))
    public int numIslandsII(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    Queue<Point> queue = new ArrayDeque<>();
                    queue.offer(new Point(r, c));
                    while (!queue.isEmpty()) {
                        Point cur = queue.poll();
                        grid[cur.x][cur.y] = '0';
                        for (int[] dir : DIRS) {
                            int x = cur.x + dir[0];
                            int y = cur.y + dir[1];
                            if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '1') {
                                queue.offer(new Point(x, y));
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Union Find Time = O(M*N) Space = O(M*N)
    public int numIslandsIII(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        // int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    for (int[] dir : DIRS) {
                        int x = r + dir[0];
                        int y = c + dir[1];
                        if (x >= 0 && x < nr && y >= 0 && y < nc && grid[x][y] == '1') {
                            uf.union(r * nc + c, x * nc + y);
                        }
                    }
                }
            }
        }
        return uf.count();
    }

    static class UnionFind {
        int count; // # of connected components
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) { // for problem 200
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 1;
                }
            }
        }

        public int find(int i) { // path compression
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int x, int y) { // union with rank
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                    rank[rootx] += rank[rooty];
                } else {
                    parent[rootx] = rooty;
                    rank[rooty] += rank[rootx];
                }
                count--;
            }
        }

        public int count() {
            return count;
        }
    }

}
