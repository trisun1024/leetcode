import java.util.*;

class StoneGameIV {

    // DP.
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int k = 1; k * k <= i; k++) {
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    // DFS + Memorization.
    public boolean winnerSquareGameI(int n) {
        HashMap<Integer, Boolean> cache = new HashMap<>();
        cache.put(0, false);
        return dfs(cache, n);
    }

    public static boolean dfs(HashMap<Integer, Boolean> cache, int remain) {
        if (cache.containsKey(remain)) {
            return cache.get(remain);
        }
        int sqrtVal = (int) Math.sqrt(remain);
        for (int i = 1; i <= sqrtVal; i++) {
            // if there is any chance to make the opponent lose the game in the next round,
            // then the current player will win.
            if (!dfs(cache, remain - i * i)) {
                cache.put(remain, true);
                return true;
            }
        }
        cache.put(remain, false);
        return false;
    }
}