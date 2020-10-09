
class CanIWin {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // base case
        if (desiredTotal <= 0) {
            return true;
        }
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }
        int[] dp = new int[1 << maxChoosableInteger];
        return dfs(dp, 0, maxChoosableInteger, desiredTotal);
    }

    private boolean dfs(int[] dp, int index, int max, int target) {
        if (dp[index] != 0) {
            return dp[index] > 0;
        }
        if (target <= 0) {
            dp[index] = -1;
            return false;
        }
        for (int i = 1; i <= max; i++) {
            if ((index >> (i - 1) & 1) == 1)
                continue;
            if (!dfs(dp, (1 << (i - 1)) | index, max, target - i)) {
                dp[index] = 1;
                return true;
            }
        }
        dp[index] = -1;
        return false;
    }
}