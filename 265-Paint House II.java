import java.util.*;

class Solution {

    // Memorization. Time = O(N*K^2); Space = O(N*K);
    public int minCostII(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int m = costs.length;
        int n = costs[0].length;
        Map<String, Integer> memo = new HashMap<>();
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minCost = Math.min(minCost, dfs(costs, 0, i, m, n, memo));
        }
        return minCost;
    }

    private int dfs(int[][] costs, int houseNumber, int color, int m, int n, Map<String, Integer> memo) {
        if (houseNumber == m - 1) {
            return costs[houseNumber][color];
        }
        if (memo.containsKey(getKey(houseNumber, color))) {
            return memo.get(getKey(houseNumber, color));
        }
        int minRemainingCost = Integer.MAX_VALUE;
        for (int nextColor = 0; nextColor < n; nextColor++) {
            if (color == nextColor) {
                continue;
            }
            int curRemainingCost = dfs(costs, houseNumber + 1, nextColor, m, n, memo);
            minRemainingCost = Math.min(minRemainingCost, curRemainingCost);
        }
        int totalCost = costs[houseNumber][color] + minRemainingCost;
        memo.put(getKey(houseNumber, color), totalCost);
        return totalCost;
    }

    private String getKey(int n, int color) {
        StringBuilder sb = new StringBuilder();
        sb.append(n);
        sb.append(',');
        sb.append(color);
        return sb.toString();
    }

    // DP. Time = O(N*K^2); Space = O(1);
    public int minCostIII(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int m = costs.length;
        int n = costs[0].length;
        for (int house = 1; house < m; house++) {
            for (int color = 0; color < n; color++) {
                int min = Integer.MAX_VALUE;
                for (int prev = 0; prev < n; prev++) {
                    if (color == prev) {
                        continue;
                    }
                    min = Math.min(min, costs[house - 1][prev]);
                }
                costs[house][color] += min;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int c : costs[m - 1]) {
            min = Math.min(min, c);
        }
        return min;
    }

    // DP with optimized time and space. Time = O(N*K); Space = O(1);
    public int minCostIIII(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int m = costs.length;
        int n = costs[0].length;
        int prevMin = -1;
        int prevSecMin = -1;
        int prevMinColor = -1;
        for (int color = 0; color < n; color++) {
            int cost = costs[0][color];
            if (prevMin == -1 || cost < prevMin) {
                prevSecMin = prevMin;
                prevMinColor = color;
                prevMin = cost;
            } else if (prevSecMin == -1 || cost < prevSecMin) {
                prevSecMin = cost;
            }
        }
        for (int house = 1; house < m; house++) {
            int min = -1;
            int secondMin = -1;
            int minColor = -1;
            for (int color = 0; color < n; color++) {
                // Determine the cost for this cell (without writing it in).
                int cost = costs[house][color];
                if (color == prevMinColor) {
                    cost += prevSecMin;
                } else {
                    cost += prevMin;
                }
                // Determine whether or not this current cost is also a minimum.
                if (min == -1 || cost < min) {
                    secondMin = min;
                    minColor = color;
                    min = cost;
                } else if (secondMin == -1 || cost < secondMin) {
                    secondMin = cost;
                }
            }
            // Transfer current mins to be previous mins.
            prevMin = min;
            prevSecMin = secondMin;
            prevMinColor = minColor;
        }

        return prevMin;
    }
}