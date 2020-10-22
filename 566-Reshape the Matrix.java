import java.util.*;

class ReshapeTheMatrix {

    // Queue Extra Space Storage. Time = O(M*N); Space = O(M*N);
    public int[][] matrixReshapeI(int[][] nums, int r, int c) {
        if (nums.length == 0 || r * c != nums.length * nums[0].length) {
            return nums;
        }
        int[][] res = new int[r][c];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                queue.offer(nums[i][j]);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = queue.poll();
            }
        }
        return res;
    }

    // Inplace. Time = O(M*N); Space = O(1);
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        // base case
        if (nums.length == 0 || r * c != nums.length * nums[0].length) {
            return nums;
        }
        int[][] res = new int[r][c];
        int rows = 0, cols = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                res[rows][cols] = nums[i][j];
                cols++;
                if (cols == c) {
                    rows++;
                    cols = 0;
                }
            }
        }
        return res;
    }

    public int[][] matrixReshapeII(int[][] nums, int r, int c) {
        // base case
        if (nums.length == 0 || r * c != nums.length * nums[0].length) {
            return nums;
        }
        int[][] res = new int[r][c];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                res[count / c][count % c] = nums[i][j];
                count++;
            }
        }
        return res;
    }
}
