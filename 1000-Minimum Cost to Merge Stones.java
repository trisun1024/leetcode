import java.util.*;

class Solution {

    // DFS
    public int mergeStones(int[] stones, int k) {
        // test it is possible to solve
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }
        // create a prefix sum array to fast access
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i+1] = prefix[i] + stones[i];
        }
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, -1);
        }
        return helper(cost, prefix, 0, n - 1, k);
    }

    private int helper(int[][] cost, int[] prefix, int left, int right, int k) {
        if (left == right) {
            return 0;
        }
        if (cost[left][right] != -1) {
            return cost[left][right];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = left; i < right; i += k - 1) {
            ans = Math.min(ans, helper(cost, prefix, left, i, k) + helper(cost, prefix, i+1, right, k));
        }
        if ((right - left) % (k - 1) == 0) {
            ans += prefix[right + 1] - prefix[left];
        }
        cost[left][right] = ans;
        return ans;
    }
}