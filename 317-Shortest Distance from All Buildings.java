import java.util.*;

class ShortestDistanceFromAllBuildings {

    // BFS

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        int[][] reachCount = new int[m][n];
        int houseCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    houseCount++;
                }
            }
        }
        // find and calculate min dist
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // houseCount++;
                    if (!bfs(grid, dist, reachCount, houseCount, m, n, i, j)) {
                        return -1;
                    }
                }
            }
        }
        // find solution
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reachCount[i][j] == houseCount) {
                    minDistance = Math.min(minDistance, dist[i][j]);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private boolean bfs(int[][] grid, int[][] dist, int[][] reachCount, int houseCount, int m, int n, int x, int y) {
        boolean[][] visited = new boolean[m][n];
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(x, y));
        int level = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();
                for (int[] dir : DIRS) {
                    int nx = cur.x + dir[0];
                    int ny = cur.y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                        if (grid[nx][ny] == 0) {
                            dist[nx][ny] += level;
                            visited[nx][ny] = true;
                            reachCount[nx][ny]++;
                            queue.offer(new Point(nx, ny));
                        } else if (grid[nx][ny] == 1) {
                            count++;
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
        }
        return count == houseCount;
    }

    private final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}