import java.util.*;

class RottingOranges {

    // BFS
    final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int orangesRotting(int[][] grid) {
        Queue<Point> queue = new ArrayDeque<>();
        int fresh = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new Point(r, c));
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }
        int time = 0;
        while (!queue.isEmpty() && fresh > 0) {
            time++;
            int size = queue.size();
            while (size > 0) {
                Point cur = queue.poll();
                for (int[] dir : DIRS) {
                    int nx = cur.x + dir[0];
                    int ny = cur.y + dir[1];
                    if (nx >= 0 && nx < row && ny >= 0 && ny < col) {
                        if (grid[nx][ny] == 1) {
                            grid[nx][ny] = 2;
                            fresh--;
                            queue.offer(new Point(nx, ny));
                        }
                    }
                }
                size--;
            }
        }
        return fresh == 0 ? time : -1;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}