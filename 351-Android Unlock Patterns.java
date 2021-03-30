class AndroidUnlockPatterns {

    // 1D array to test.
    public int numberOfPatterns(int m, int n) {
        int count = 0;
        for (int len = m; len <= n; len++) {
            count += calculate(-1, len, new boolean[9]);
        }
        return count;
    }

    private int calculate(int last, int len, boolean[] used) {
        if (len == 0) {
            return 1;
        }
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            if (isValid(i, last, used)) {
                used[i] = true;
                sum += calculate(i, len - 1, used);
                used[i] = false;
            }
        }
        return sum;
    }

    private boolean isValid(int index, int last, boolean[] used) {
        if (used[index]) {
            return false;
        }
        if (last == -1) {
            return true;
        }
        if ((index + last) % 2 == 1) {
            return true;
        }
        int mid = (index + last) / 2;
        if (mid == 4) {
            return used[mid];
        }
        if ((index % 3 != last % 3) && (index / 3 != last / 3)) {
            return true;
        }
        return used[mid];
    }

    // other solution if we find all skip mapping
    public int numberOfPatternsII(int m, int n) {
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean visited[] = new boolean[10];
        int res = 0;
        for (int len = m; len <= n; len++) {
            res += dfs(visited, skip, 1, len - 1) * 4;
            res += dfs(visited, skip, 2, len - 1) * 4;
            res += dfs(visited, skip, 5, len - 1);
        }
        return res;
    }

    private int dfs(boolean[] visited, int[][] skip, int cur, int remain) {
        if (remain < 0) {
            return 0;
        }
        if (remain == 0) {
            return 1;
        }
        visited[cur] = true;
        int res = 0;
        for (int i = 1; i <= 9; i++) {
            if (!visited[i] && (skip[cur][i] == 0 || visited[skip[cur][i]])) {
                res += dfs(visited, skip, i, remain - 1);
            }
        }
        visited[cur] = false;
        return res;
    }
}