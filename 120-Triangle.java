import java.util.*;

class Solution {

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

    // DP Time = O(M*N); Space = O(M);
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
}