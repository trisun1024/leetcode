import java.util.*;

class ShortestPathInBinaryMatrix {

    private final int[][] DIRS = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
            { 1, 1 } };

    public int shortestPathBinaryMatrix(int[][] grid) {

        // Firstly, we need to check that the start and target cells are open.
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }

        // Set up the BFS.
        Queue<int[]> queue = new ArrayDeque<>();
        grid[0][0] = 1;
        queue.add(new int[] { 0, 0 });

        // Carry out the BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            int row = cell[0];
            int col = cell[1];
            int distance = grid[row][col];
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return distance;
            }
            for (int[] neighbour : getNeighbours(row, col, grid)) {
                int neighbourRow = neighbour[0];
                int neighbourCol = neighbour[1];
                queue.add(new int[] { neighbourRow, neighbourCol });
                grid[neighbourRow][neighbourCol] = distance + 1;
            }
        }

        // The target was unreachable.
        return -1;
    }

    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;
            }
            neighbours.add(new int[] { newRow, newCol });
        }
        return neighbours;
    }

    // BFS.
    public int shortestPathBinaryMatrixI(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0 });

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pop = queue.poll();
                if (pop[0] == m - 1 && pop[1] == n - 1) {
                    return ans + 1;
                }
                for (int k = 0; k < 8; k++) {
                    int nextX = DIRS[k][0] + pop[0];
                    int nextY = DIRS[k][1] + pop[1];

                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]
                            && grid[nextX][nextY] == 0) {
                        queue.offer(new int[] { nextX, nextY });
                        visited[nextX][nextY] = true;
                    }

                }
            }
            ans++;
        }

        return -1;
    }
}