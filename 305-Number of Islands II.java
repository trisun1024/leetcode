import java.util.*;

class NumberOfIslandsII {

    // DFS
    public List<Integer> numIsland(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        char[][] grid = new char[m][n];
        for (char[] row : grid) {
            Arrays.fill(row, '0');
        }
        for (int[] pos : positions) {
            grid[pos[0]][pos[1]] = '1';
            ans.add(numIslands(grid));
        }
        return ans;
    }

    private void dfs(char[][] grid, int r, int c, boolean[][] visited) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0' || visited[r][c]) {
            return;
        }

        visited[r][c] = true;
        dfs(grid, r - 1, c, visited);
        dfs(grid, r + 1, c, visited);
        dfs(grid, r, c - 1, visited);
        dfs(grid, r, c + 1, visited);
    }

    private int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        boolean[][] visited = new boolean[nr][nc];
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    ++num_islands;
                    dfs(grid, r, c, visited);
                }
            }
        }

        return num_islands;
    }

    // Union Find
    int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] matrix = new int[m][n];
        List<Integer> result = new ArrayList<>();
        int islands = 0;

        UnionFind uf = new UnionFind((m + 1) * (n + 1));

        for (int[] v : positions) {
            int i = v[0], j = v[1];
            if (matrix[i][j] == 1) {
                // lol, no sea has converted to land
                result.add(islands);
                continue;
            }
            List<Integer> adj = adjacent(i, j, m, n, matrix);
            if (adj.isEmpty()) {
                // there are not adjancent land to connect, this is a new island
                islands++;
            } else {
                // merge current land with any one of adjancent lands
                uf.union(i * n + j, adj.get(0));
                int last = adj.get(0);
                for (int k = 1; k < adj.size(); k++) {
                    if (!uf.connected(last, adj.get(k))) {
                        /*
                         * these two adjacents are not connected but current land is gonna merge these
                         * two islands.
                         */
                        islands--;
                    }
                    uf.union(i * n + j, adj.get(k));
                }
            }
            matrix[i][j] = 1;
            result.add(islands);
        }
        return result;
    }

    private List<Integer> adjacent(int x, int y, int m, int n, int[][] matrix) {
        List<Integer> adj = new ArrayList<>();
        for (int[] d : dir) {
            int i = x + d[0];
            int j = y + d[1];
            if (i >= 0 && j >= 0 && i < m && j < n && matrix[i][j] == 1) {
                adj.add(i * n + j);
            }
        }
        return adj;
    }

    class UnionFind {
        int[] id;

        public UnionFind(int n) {
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
        }

        public boolean connected(int u, int v) {
            return find(u) == find(v);
        }

        public void union(int u, int v) {
            id[find(u)] = find(v);
        }

        private int find(int v) {
            while (v != id[v]) {
                id[v] = id[id[v]];
                v = id[v];
            }
            return v;
        }
    }
}