import java.util.*;

class PathWithMaximumMinimumValue {

    private final int[][] DIRS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    // Heap. Time = O(M*N*log(M*N)); Space = O(M*N);
    public int maximumMinimumPath(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        boolean[][] visited = new boolean[row][col];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        maxHeap.offer(new int[] { 0, 0, A[0][0] });
        while (!maxHeap.isEmpty()) {
            int[] cur = maxHeap.poll();
            if (cur[0] == row - 1 && cur[1] == col - 1) {
                return cur[2];
            }
            visited[cur[0]][cur[1]] = true;
            for (int[] dir : DIRS) {
                int nr = cur[0] + dir[0];
                int nc = cur[1] + dir[1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col && !visited[nr][nc]) {
                    maxHeap.offer(new int[] { nr, nc, Math.min(A[nr][nc], cur[2]) });
                }
            }
        }
        return -1;
    }

    // Union Find. Time = O(M*N*log(M*N)); Space = O(M*N);
    public int maximumMinimumPathI(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                list.add(new int[] { i, j });
            }
        }
        list.sort((a, b) -> A[b[0]][b[1]] - A[a[0]][a[1]]);
        UnionFind uf = new UnionFind(row * col);
        boolean[][] visited = new boolean[row][col];
        for (int[] arr : list) {
            int x = arr[0];
            int y = arr[1];
            visited[x][y] = true;
            for (int[] dir : DIRS) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && visited[nx][ny]) {
                    uf.union(x * col + y, nx * col + ny);
                }
                if (uf.find(0) == uf.find(row * col - 1)) {
                    return A[x][y];
                }
            }
        }
        return -1;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int nx = find(x);
            int ny = find(y);
            if (nx == ny) {
                return;
            }
            if (rank[ny] > rank[nx]) {
                parent[nx] = ny;
            } else {
                parent[ny] = nx;
                if (rank[nx] == rank[ny]) {
                    rank[nx]++;
                }
            }
        }
    }

    // Binary Search + DFS. Time = O(M*N*log(M*N)); Space = O(M*N);
    public int maximumMinimumPathII(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        // step 1: sort coordinates by their value, O(mn log(mn));
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (A[i][j] <= Math.min(A[0][0], A[row - 1][col - 1])) {
                    list.add(new int[] { i, j, A[i][j] });
                }
            }
        }
        list.sort((a, b) -> a[2] - b[2]);
        // step 2: binary search, O(log(mn));
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (dfs(A, new boolean[row][col], 0, 0, list.get(mid)[2], row, col)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return list.get(r)[2];
    }

    // check if there's a path from (0,0) to (n-1, m-1) where "min" is the minumum
    // value on the path, O(mn);
    private boolean dfs(int[][] A, boolean[][] seen, int x, int y, int min, int row, int col) {
        if (x == row - 1 && y == col - 1) {
            return true;
        }
        seen[x][y] = true;
        for (int[] dir : DIRS) {
            int xx = x + dir[0];
            int yy = y + dir[1];
            if (xx >= 0 && xx < row && yy >= 0 && yy < col && !seen[xx][yy] && A[xx][yy] >= min) {
                if (dfs(A, seen, xx, yy, min, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }
}