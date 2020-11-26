import java.util.*;

class MinimumKnightMoves {

    // BFS
    private final int[][] DIRECTIONS = new int[][] { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { -1, -2 },
            { 1, -2 }, { 2, -1 } };

    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 0, 0 });

        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.remove();
                int curX = cur[0];
                int curY = cur[1];
                if (curX == x && curY == y) {
                    return result;
                }

                for (int[] d : DIRECTIONS) {
                    int newX = curX + d[0];
                    int newY = curY + d[1];
                    if (!visited.contains(newX + "," + newY) && newX >= -1 && newY >= -1) {
                        queue.add(new int[] { newX, newY });
                        visited.add(newX + "," + newY);
                    }
                }
            }
            result++;
        }
        return -1;
    }

    // math solution
    public int minKnightMovesII(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        if (x < y) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (x == 1 && y == 0)
            return 3;
        if (x == 2 && y == 2)
            return 4;
        // int del = x - y;
        if (2 * y > x) {
            return (int) (x - y - 2 * Math.floor((float) (x - 2 * y) / 3));
        } else
            return (int) (x - y - 2 * Math.floor((x - 2 * y) / 4));

    }
}