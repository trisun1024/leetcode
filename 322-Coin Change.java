import java.util.*;

class CoinChange {

    // DP Top Down. Time = O(S*n), where S is the amount, n is denomination count;
    public int coinChangeI(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return dfs(coins, amount, new int[amount]);
    }

    private int dfs(int[] coins, int rem, int[] dp) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (dp[rem - 1] != 0) {
            return dp[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dfs(coins, rem - coin, dp);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        dp[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return dp[rem - 1];
    }

    // DP Bottom Up.
    public int coinChange(int[] coins, int amount) {
        int[] changes = new int[amount + 1];
        Arrays.fill(changes, Integer.MAX_VALUE);
        changes[0] = 0;
        for (int i = 0; i < coins.length; ++i) {
            for (int j = coins[i]; j <= amount; ++j) {
                if (changes[j - coins[i]] != Integer.MAX_VALUE) {
                    changes[j] = Math.min(changes[j], changes[j - coins[i]] + 1);
                }
            }
        }
        return changes[amount] == Integer.MAX_VALUE ? -1 : changes[amount];
    }

    public int coinChangeII(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}