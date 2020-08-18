import java.util.*;

class CoinChange {

    // DP
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
}