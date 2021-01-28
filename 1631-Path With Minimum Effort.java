import java.util.*;

class PathWithMinimumEffort {

    private final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // Dijkstra's Algorithm. Time = O(M*N*log(M*N)); Space = O(M*N);
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        int[][] diffMatrix = new int[row][col];
        for (int[] arr : diffMatrix) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        diffMatrix[0][0] = 0;
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> (a.diff - b.diff));
        boolean[][] visited = new boolean[row][col];
        pq.offer(new Cell(0, 0, diffMatrix[0][0]));
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            visited[cur.x][cur.y] = true;
            if (cur.x == row - 1 && cur.y == col - 1) {
                return cur.diff;
            }
            for (int[] dir : DIRS) {
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];
                if (isValid(nx, ny, row, col) && !visited[nx][ny]) {
                    int curDiff = Math.abs(heights[nx][ny] - heights[cur.x][cur.y]);
                    int maxDiff = Math.max(curDiff, diffMatrix[cur.x][cur.y]);
                    if (diffMatrix[nx][ny] > maxDiff) {
                        diffMatrix[nx][ny] = maxDiff;
                        pq.offer(new Cell(nx, ny, maxDiff));
                    }
                }
            }

        }
        return diffMatrix[row - 1][col - 1];
    }

    private boolean isValid(int x, int y, int row, int col) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    static class Cell {
        int x;
        int y;
        int diff;

        Cell(int x, int y, int diff) {
            this.x = x;
            this.y = y;
            this.diff = diff;
        }
    }

    // Union Find. Time = O()
    public int minimumEffortPathI(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;
        // base case.
        if (row == 1 && col == 1) {
            return 0;
        }
        // construct union find graph
        UnionFind unionFind = new UnionFind(heights);
        List<Cell> edgeList = unionFind.edgeList;
        Collections.sort(edgeList, (e1, e2) -> e1.diff - e2.diff);

        for (int i = 0; i < edgeList.size(); i++) {
            int x = edgeList.get(i).x;
            int y = edgeList.get(i).y;
            unionFind.union(x, y);
            if (unionFind.find(0) == unionFind.find(row * col - 1)) {
                return edgeList.get(i).diff;
            }
        }
        return -1;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        List<Cell> edgeList;

        public UnionFind(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            parent = new int[row * col];
            edgeList = new ArrayList<>();
            rank = new int[row * col];
            for (int currentRow = 0; currentRow < row; currentRow++) {
                for (int currentCol = 0; currentCol < col; currentCol++) {
                    if (currentRow > 0) {
                        edgeList.add(new Cell(currentRow * col + currentCol, (currentRow - 1) * col + currentCol,
                                Math.abs(heights[currentRow][currentCol] - heights[currentRow - 1][currentCol])));
                    }
                    if (currentCol > 0) {
                        edgeList.add(new Cell(currentRow * col + currentCol, currentRow * col + currentCol - 1,
                                Math.abs(heights[currentRow][currentCol] - heights[currentRow][currentCol - 1])));
                    }
                    parent[currentRow * col + currentCol] = currentRow * col + currentCol;
                }
            }
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                if (rank[parentX] > rank[parentY])
                    parent[parentY] = parentX;
                else if (rank[parentX] < rank[parentY])
                    parent[parentX] = parentY;
                else {
                    parent[parentY] = parentX;
                    rank[parentX] += 1;
                }
            }
        }
    }

    // Binary Search + BFS. Time = O(M*N); Space = O(M*N);
    public int minimumEffortPathII(int[][] heights) {
        int left = 0;
        int right = 1000000;
        int result = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canReachDestinaton(heights, mid)) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // use bfs to check if we can reach destination with max absolute difference k
    boolean canReachDestinaton(int[][] heights, int k) {
        int row = heights.length;
        int col = heights[0].length;
        Deque<Location> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        queue.addLast(new Location(0, 0));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Location curr = queue.removeFirst();
            if (curr.x == row - 1 && curr.y == col - 1) {
                return true;
            }
            for (int[] dir : DIRS) {
                int adjacentX = curr.x + dir[0];
                int adjacentY = curr.y + dir[1];
                if (isValid(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                    if (currentDifference <= k) {
                        visited[adjacentX][adjacentY] = true;
                        queue.addLast(new Location(adjacentX, adjacentY));
                    }
                }
            }
        }
        return false;
    }

    static class Location {
        int x;
        int y;

        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    // Binary Search + DFS. Time = O(M*N); Space = O(M*N);
    public int minimumEffortPathIII(int[][] heights) {
        int left = 0;
        int right = 1000000;
        int result = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (dfs(heights, mid)) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private boolean dfs(int[][] heights, int mid) {
        int row = heights.length;
        int col = heights[0].length;
        boolean visited[][] = new boolean[row][col];
        return canReachDestinaton(0, 0, heights, visited, row, col, mid);
    }

    private boolean canReachDestinaton(int x, int y, int[][] heights, boolean[][] visited, int row, int col, int mid) {
        if (x == row - 1 && y == col - 1) {
            return true;
        }
        visited[x][y] = true;
        for (int[] dir : DIRS) {
            int adjacentX = x + dir[0];
            int adjacentY = y + dir[1];
            if (isValid(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[x][y]);
                if (currentDifference <= mid) {
                    if (canReachDestinaton(adjacentX, adjacentY, heights, visited, row, col, mid))
                        return true;
                }
            }
        }
        return false;
    }

}