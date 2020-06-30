import java.util.*;

class Solution {

    // 2D array T = O(M*N) S = O(M*N)
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // 1D array T = O(M*N) S = O(M*N)
    public int uniquePathsII(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                } else {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }

    // Math solution
    public int uniquePathsIII(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        m--;
        n--;
        if (m < n) {
            m = m + n;
            n = m - n;
            m = m - n;
        }
        long res = 1;
        int j = 1;
        for (int i = m + 1; i <= m + n; i++, j++) {
            res *= i;
            res /= j;
        }
        return (int) res;
    }

    // follow up: a rectange, is there a path go over all three points?
    public boolean canReach(int[][] points) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[] { 0, 0 });
        for (int[] point : points) {
            list.add(point);
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) {
                    return 0;
                }
                return a[1] < b[1] ? -1 : 1;
            }
        });
        for (int i = 1; i < list.size(); i++) {
            int[] cur = list.get(i);
            int[] prev = list.get(i - 1);
            if (cur[1] == prev[1]) {
                return false;
            }
            int len = cur[1] - prev[1];
            int upper = prev[0] - len;
            int lower = prev[0] + len;
            if (cur[0] <= lower && cur[0] >= upper) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    // follow up: number of paths
    public int uniqueReach(int m, int n, int[][] points) {
        int[][] dp = new int[m][n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] point : points) {
            if (map.containsKey(point[1])) {
                return 0;
            } else {
                map.put(point[1], point[0]);
            }
        }
        int res = 0;
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
            if (map.containsKey(i)) {
                int row = map.get(i);
                for (int j = 0; j < n; j++) {
                    if (j != row) {
                        dp[i][j] = 0;
                    } else {
                        res = dp[i][j];
                    }
                }
            }
        }
        return res;
    }

}