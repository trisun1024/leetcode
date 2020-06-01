import java.util.*;

class Solution {

    // Queue
    // key point: think as an array only think about transfer array to matrix
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        // buid map store the current index and how many steps
        Map<Integer, Integer> dist = new HashMap<>();
        // initial state
        dist.put(1, 0);
        // build a Queue to store the index
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // if current index reaches the last, then we return the number of steps
            if (cur == n * n) {
                return dist.get(cur);
            }
            for (int next = cur + 1; next <= Math.min(cur + 6, n * n); next++) {
                int[] rc = convertArrayToBoard(next, n);
                int r = rc[0];
                int c = rc[1];
                int nextPos = board[r][c] == -1 ? next : board[r][c];
                if (!dist.containsKey(nextPos)) {
                    dist.put(nextPos, dist.get(cur) + 1);
                    queue.offer(nextPos); 
                }
            }
        }
        return -1;
    }

    private int[] convertArrayToBoard(int index, int n) {
        int quot = (index - 1) / n;
        int rem = (index - 1) % n;
        int row = n - 1 - quot;
        int col = row % 2 != n % 2 ? rem : n - 1 - rem;
        return new int[] { row, col };
    }

    //
}