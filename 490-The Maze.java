import java.util.*;

class Maze {

    // DFS. Time =O(M*N);
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }

    private boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if (visited[start[0]][start[1]]) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }
        visited[start[0]][start[1]] = true;
        int l = start[1] - 1;
        int r = start[1] + 1;
        int u = start[0] - 1;
        int d = start[1] + 1;
        while (l >= 0 && maze[start[0]][l] == 0) {
            l--;
        }
        if (dfs(maze, new int[] { start[0], l + 1 }, destination, visited)) {
            return true;
        }
        while (r < maze[0].length && maze[start[0]][r] == 0) {
            r++;
        }
        if (dfs(maze, new int[] { start[0], r - 1 }, destination, visited)) {
            return true;
        }
        while (u >= 0 && maze[u][start[1]] == 0) {
            u--;
        }
        if (dfs(maze, new int[] { u + 1, start[1] }, destination, visited)) {
            return true;
        }
        while (d < maze.length && maze[d][start[1]] == 0) {
            d++;
        }
        if (dfs(maze, new int[] { d - 1, start[1] }, destination, visited)) {
            return true;
        }
        return false;
    }

    // BFS. Time =O(M*N);
    private final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public boolean hasPathI(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return true;
            }
            for (int[] dir : DIRS) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                if (!visited[x - dir[0]][y - dir[1]]) {
                    queue.offer(new int[] { x - dir[0], y - dir[1] });
                    visited[x - dir[0]][y - dir[1]] = true;
                }
            }
        }
        return false;
    }
}