import java.util.*;

class MaximumVacationDays {

    // DFS. Time = O(N^K); Space = O(K);
    public int maxVacationDaysI(int[][] flights, int[][] days) {
        return dfs(flights, days, 0, 0);
    }

    private int dfs(int[][] flights, int[][] days, int curCity, int weekNumber) {
        if (weekNumber == days[0].length) {
            return 0;
        }
        int maxVac = 0;
        for (int i = 0; i < flights.length; i++) {
            if (flights[curCity][i] == 1 || i == curCity) {
                int vac = days[i][weekNumber] + dfs(flights, days, i, weekNumber + 1);
                maxVac = Math.max(maxVac, vac);
            }
        }
        return maxVac;
    }

    // DFS + Memorization. Time = O(N^2*K); Space = O(N*K);
    public int maxVacationII(int[][] flights, int[][] days) {
        int[][] memo = new int[flights.length][days[0].length];
        for (int[] m : memo) {
            Arrays.fill(m, Integer.MIN_VALUE);
        }
        return helper(flights, days, 0, 0, memo);
    }

    private int helper(int[][] flights, int[][] days, int curCity, int weekNumber, int[][] memo) {
        if (weekNumber == days[0].length) {
            return 0;
        }
        if (memo[curCity][weekNumber] != Integer.MIN_VALUE) {
            return memo[curCity][weekNumber];
        }
        int maxVac = 0;
        for (int i = 0; i < flights.length; i++) {
            if (flights[curCity][i] == 1 || i == curCity) {
                int vac = days[i][weekNumber] + helper(flights, days, curCity, weekNumber + 1, memo);
                maxVac = Math.max(maxVac, vac);
            }
        }
        memo[curCity][weekNumber] = maxVac;
        return maxVac;
    }

    // 2D DP.
    public int maxVacation(int[][] flights, int[][] days) {
        int n = flights.length;
        int k = days[0].length;
        int[][] dp = new int[n][k + 1];
        for (int w = k - 1; w >= 0; w--) {
            for (int c = 0; c < n; c++) { // c -- city
                int max = 0;
                for (int nc = 0; nc < n; nc++) { // nc -- next city
                    if (flights[c][nc] == 1 || c == nc) {
                        max = Math.max(days[nc][w] + dp[nc][w + 1], max);
                    }
                }
                dp[c][w] = max;
            }
        }

        return dp[0][0];
    }
}