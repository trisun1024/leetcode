import java.util.*;

class Triangle {

    // Recursion
    public int minimumTotalII(List<List<Integer>> triangle) {
        int[] cur = new int[] { 0 };
        int[] min = new int[] { Integer.MAX_VALUE };
        helper(triangle, 0, 0, cur, min);
        return min[0];
    }

    private void helper(List<List<Integer>> triangle, int i, int index, int[] sum, int[] min) {
        if (i == triangle.size()) {
            min[0] = Math.min(min[0], sum[0]);
            return;
        }
        List<Integer> list = triangle.get(i);
        for (int j = index; j < list.size() && j <= index + 1; j++) {
            sum[0] += list.get(j);
            helper(triangle, i + 1, j, sum, min);
            sum[0] -= list.get(j);
        }
    }

    // DP + Bottom to Top. Time = O(M*N); Space = O(M);
    /*
     * M[i] represents current triangle level minimum values combine with previous
     * level.
     * 4 1 8 3
     * 7 6 10
     * 9 10
     * 11
     * 
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] dp = new int[size + 1];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    // Improve with In-place.
    public int minimumTotalI(List<List<Integer>> triangle) {
        int size = triangle.size();
        for (int r = size - 2; r >= 0; r--) {
            for (int c = 0; c < triangle.get(r).size(); c++) {
                int min = Math.min(triangle.get(r + 1).get(c), triangle.get(r + 1).get(c + 1));
                triangle.get(r).set(c, min + triangle.get(r).get(c));
            }
        }
        return triangle.get(0).get(0);
    }
}