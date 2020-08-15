import java.util.*;

class SmallestRectangleEnclosingBlackPixels {

    // Traverse all blocks. Time = O(M*N);
    public int minArea(char[][] image, int x, int y) {
        int top = x, bottom = x;
        int left = y, right = y;
        for (x = 0; x < image.length; ++x) {
            for (y = 0; y < image[0].length; ++y) {
                if (image[x][y] == '1') {
                    top = Math.min(top, x);
                    bottom = Math.max(bottom, x + 1);
                    left = Math.min(left, y);
                    right = Math.max(right, y + 1);
                }
            }
        }
        return (right - left) * (bottom - top);
    }

    // BFS Queue. Time = O(M*N)
    private final int[][] DIRS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public int minAreaI(char[][] image, int x, int y) {
        int top = x;
        int bottom = x;
        int left = y;
        int right = y;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[image.length][image[0].length];
        queue.offer(new int[] { x, y });
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            top = Math.min(top, cur[0]);
            bottom = Math.max(bottom, cur[0] + 1);
            left = Math.min(left, cur[1]);
            right = Math.max(right, cur[1] + 1);
            for (int[] dir : DIRS) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];
                if (nx >= 0 && nx < image.length && ny >= 0 && ny < image[0].length && image[nx][ny] == '1'
                        && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] { nx, ny });
                }
            }
        }
        return (right - left) * (bottom - top);
    }

    // DFS. Time = O(M*N)
    private int top, bottom, left, right;

    public int minAreaII(char[][] image, int x, int y) {
        if (image.length == 0 || image[0].length == 0)
            return 0;
        top = bottom = x;
        left = right = y;
        dfs(image, x, y);
        return (right - left) * (bottom - top);
    }

    private void dfs(char[][] image, int x, int y) {
        if (x < 0 || y < 0 || x >= image.length || y >= image[0].length || image[x][y] == '0')
            return;
        image[x][y] = '0'; // mark visited black pixel as white
        top = Math.min(top, x);
        bottom = Math.max(bottom, x + 1);
        left = Math.min(left, y);
        right = Math.max(right, y + 1);
        dfs(image, x + 1, y);
        dfs(image, x - 1, y);
        dfs(image, x, y - 1);
        dfs(image, x, y + 1);
    }

    // Binary Search. Time = O(M*log(N)+N*log(M));
    public int minAreaIII(char[][] image, int x, int y) {
        int m = image.length;
        int n = image[0].length;
        int left = searchColumns(image, 0, y, 0, m, true);
        int right = searchColumns(image, y + 1, n, 0, m, false);
        int top = searchRows(image, 0, x, left, right, true);
        int bottom = searchRows(image, x + 1, m, left, right, false);
        return (right - left) * (bottom - top);
    }

    private int searchColumns(char[][] image, int i, int j, int top, int bottom, boolean whiteToBlack) {
        while (i != j) {
            int k = top;
            int mid = (i + j) / 2;
            while (k < bottom && image[k][mid] == '0') {
                ++k;
            }
            if (k < bottom == whiteToBlack) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    private int searchRows(char[][] image, int i, int j, int left, int right, boolean whiteToBlack) {
        while (i != j) {
            int k = left;
            int mid = (i + j) / 2;
            while (k < right && image[mid][k] == '0') {
                ++k;
            }
            if (k < right == whiteToBlack) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
}