import java.util.*;

class TrappingRainWaterII {

    // PriorityQueue + DP. Time = O
    public int trapRainWater(int[][] heightMap) {
        int row = heightMap.length;
        int col = heightMap[0].length;
        if (row < 3 || col < 3) {
            return 0;
        }
        PriorityQueue<Pair> minHeap = new PriorityQueue<>();
        boolean[][] visited = new boolean[row][col];
        processBorder(heightMap, visited, minHeap, row, col);
        int res = 0;
        while (!minHeap.isEmpty()) {
            Pair cur = minHeap.poll();
            List<Pair> neighbors = allNeighbors(cur, heightMap, visited);
            for (Pair nei : neighbors) {
                if (visited[nei.x][nei.y]) {
                    continue;
                }
                visited[nei.x][nei.y] = true;
                res += Math.max(cur.height - nei.height, 0);
                nei.height = Math.max(cur.height, nei.height);
                minHeap.offer(nei);
            }
        }
        return res;
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int height;

        Pair(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(Pair another) {
            if (this.height == another.height) {
                return 0;
            }
            return this.height < another.height ? -1 : 1;
        }
    }

    private void processBorder(int[][] matrix, boolean[][] visited, PriorityQueue<Pair> minHeap, int row, int col) {
        for (int j = 0; j < col; j++) {
            minHeap.offer(new Pair(0, j, matrix[0][j]));
            minHeap.offer(new Pair(row - 1, j, matrix[row - 1][j]));
            visited[0][j] = true;
            visited[row - 1][j] = true;
        }
        for (int i = 1; i < row - 1; i++) {
            minHeap.offer(new Pair(i, 0, matrix[i][0]));
            minHeap.offer(new Pair(i, col - 1, matrix[i][col - 1]));
            visited[i][0] = true;
            visited[i][col - 1] = true;
        }
    }

    private List<Pair> allNeighbors(Pair cur, int[][] matrix, boolean[][] visited) {
        List<Pair> neis = new ArrayList<>();
        if (cur.x + 1 < matrix.length) {
            neis.add(new Pair(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
        }
        if (cur.x - 1 >= 0) {
            neis.add(new Pair(cur.x - 1, cur.y, matrix[cur.x - 1][cur.y]));
        }
        if (cur.y + 1 < matrix[0].length) {
            neis.add(new Pair(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
        }
        if (cur.y - 1 >= 0) {
            neis.add(new Pair(cur.x, cur.y - 1, matrix[cur.x][cur.y - 1]));
        }
        return neis;
    }
}