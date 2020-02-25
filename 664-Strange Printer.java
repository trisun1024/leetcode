class Solution {
    public int strangePrinter(String s) {
        int size = s.length();
        int[][] dp = new int[size + 1][size + 1];
        for (int x = 1; x <= size; x++) {
            for (int y = 1; y <= x; y++) {
                dp[y][x] = x - y + 1;
                for (int z = y; z < x; z++) {
                    dp[y][x] = Math.min(dp[y][x],
                            dp[y][z - 1] + dp[z][x - 1] + (s.charAt(x - 1) != s.charAt(z - 1) ? 1 : 0));
                }
            }
        }
        return size > 0 ? dp[1][size] : 0;
    }

    // recursion
    public int strangePrinterII(String s) {
        int n = s.length();
        return turn(s.toCharArray(), new int[n][n], 0, n - 1);
    }

    private int turn(char[] s, int[][] dp, int i, int j) {
        if (i > j)
            return 0;
        if (dp[i][j] != 0)
            return dp[i][j];
        int best = turn(s, dp, i, j - 1) + 1;

        for (int k = i; k < j; k++) {
            if (s[k] == s[j])
                best = Math.min(best, turn(s, dp, i, k) + turn(s, dp, k + 1, j - 1));
        }
        dp[i][j] = best;
        return best;
    }

    // dfs
    private int[][] dp;

    public int strangePrinterIII(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = preProcess(s.toCharArray());
        int N = s.length();
        dp = new int[N][N];
        return dfs(s.toCharArray(), 0, N - 1);
    }

    private int dfs(char[] arr, int i, int j) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        dp[i][j] = dfs(arr, i, j - 1) + 1;
        for (int k = i; k < j; k++) {
            if (arr[k] != arr[j]) {
                continue;
            }
            dp[i][j] = Math.min(dp[i][j], dfs(arr, i, k) + dfs(arr, k + 1, j - 1));
        }
        return dp[i][j];
    }

    private String preProcess(char[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
}