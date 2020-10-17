import java.util.*;

class TheMazeII {

    private final int[][] DIRS = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    // DFS. Time = O(M*N*max(M,N)); Space = O(M*N);
    public int shortestDistanceI(int[][] maze, int[] start, int[] destination) {
        int row = maze.length;
        int col = maze[0].length;
        int[][] dist = new int[row][col];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;
        dfs(maze, start, dist);
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }

    private void dfs(int[][] maze, int[] cur, int[][] dist) {
        for (int[] dir : DIRS) {
            int x = cur[0] + dir[0];
            int y = cur[1] + dir[1];
            int count = 0;
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
                count++;
            }
            if (dist[cur[0]][cur[1]] + count < dist[x - dir[0]][y - dir[1]]) {
                dist[x - dir[0]][y - dir[1]] = dist[cur[0]][cur[1]] + count;
                dfs(maze, new int[] { x - dir[0], y - dir[1] }, dist);
            }
        }
    }

    // BFS.
    public int shortestDistanceII(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            for (int[] dir : DIRS) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                    queue.offer(new int[] { x - dir[0], y - dir[1] });
                }
            }
        }
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    // Dijkstra.
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        dijkstra(maze, distance, visited);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public int[] minDistance(int[][] distance, boolean[][] visited) {
        int[] min = { -1, -1 };
        int min_val = Integer.MAX_VALUE;
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                if (!visited[i][j] && distance[i][j] < min_val) {
                    min = new int[] { i, j };
                    min_val = distance[i][j];
                }
            }
        }
        return min;
    }

    public void dijkstra(int[][] maze, int[][] distance, boolean[][] visited) {
        while (true) {
            int[] s = minDistance(distance, visited);
            if (s[0] < 0) {
                break;
            }
            visited[s[0]][s[1]] = true;
            for (int[] dir : DIRS) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
                }
            }
        }
    }

    // Dijkstra. Queue
    public int shortestDistanceIII(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length, cols = maze[0].length;
        int s = start[0] * cols + start[1];
        int target = destination[0] * cols + destination[1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        boolean[] visited = new boolean[rows * cols];
        // length 2 array: first element -> index (1D), 2nd element -> path cost
        pq.offer(new int[] { s, 0 });
        // visited[s] = true;
        while (!pq.isEmpty()) {

            // Dijkstra algorithm: the time we pop some element from the priroty queue
            // we are guaranteed to find the minimum path cost to it
            int[] curr = pq.poll();
            visited[curr[0]] = true;
            if (curr[0] == target)
                return curr[1];

            int currIdx = curr[0], currCost = curr[1];
            int r = currIdx / cols, c = currIdx % cols;
            for (int[] dir : DIRS) {
                int rr = r, cc = c, num = 0;
                while (isValid(rr + dir[0], cc + dir[1], rows, cols, maze)) {
                    rr += dir[0];
                    cc += dir[1];
                    num += 1;
                }
                if (num == 0)
                    continue;
                int next = rr * cols + cc;
                if (visited[next])
                    continue;
                pq.offer(new int[] { next, currCost + num });
            }
        }
        return -1;
    }

    private boolean isValid(int r, int c, int rows, int cols, int[][] maze) {
        return r >= 0 && r < rows && c >= 0 && c < cols && maze[r][c] == 0;
    }

}