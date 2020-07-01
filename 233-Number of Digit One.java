class NumberOfDigitOne {

    // Math
    public int countDigitOne(int n) {
        int ones = 0;
        for (long m = 1; m <= n; m *= 10)
            ones += (n / m + 8) / 10 * m + (n / m % 10 == 1 ? n % m + 1 : 0);
        return ones;
    }

    // recursion
    public int countDigitOneI(int n) {
        if (n <= 0)
            return 0;
        int multiplier = 1;
        while (multiplier <= n / 10)
            multiplier *= 10;
        if (multiplier == 1)
            return 1;
        int mod = n % multiplier;
        int div = n / multiplier;
        int add = div == 1 ? mod + 1 : multiplier;
        return add + div * countDigitOne(multiplier - 1) + countDigitOne(mod);
    }

    // DP
    public int countDigitOneII(int n) {
        if (n <= 0) return 0;
        int[] digits = new int[10];        
        int pos = 0;
        while (n > 0) {
            digits[pos++] = n % 10;
            n /= 10;
        }
        return dfs(digits, pos - 1, new int[10][10], 0, true);
    }
    
    public int dfs(int[] digits, int pos, int[][] dp, int cnt, boolean limit) {
        if (pos == -1) return cnt;
        if (!limit && dp[pos][cnt] != 0) return dp[pos][cnt];
        int up = limit ? digits[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; i++) {
            ans += dfs(digits, pos - 1, dp, cnt + (i == 1 ? 1 : 0), limit && i == up);
        }
        if (!limit) dp[pos][cnt] = ans;
        return ans;
    }
    
}