import java.util.*;

class WallsAndGates {

    private final int EMPTY = Integer.MAX_VALUE;
    private final int GATE = 0;
    private final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    // BFS
    public void wallsAndGates(int[][] rooms) {
        int row = rooms.length;
        if (row == 0) {
            return;
        }
        int col = rooms[0].length;
        Queue<Pair> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == GATE) {
                    queue.offer(new Pair(i, j));
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                Pair cur = queue.poll();
                for (int[] dir : DIRS) {
                    int nx = cur.x + dir[0];
                    int ny = cur.y + dir[1];
                    if (nx < 0 || nx >= row || ny < 0 || ny >= col || rooms[nx][ny] != EMPTY) {
                        continue;
                    }
                    rooms[nx][ny] = rooms[cur.x][cur.y] + 1;
                    queue.offer(new Pair(nx, ny));
                }
            }
        }
    }

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}