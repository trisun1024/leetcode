import java.util.*;

class FrogJump {

    // Brute Force.
    public boolean canCrossI(int[] stones) {
        return helper(stones, 0, 0);
    }

    private boolean helper(int[] stones, int start, int jumps) {
        for (int i = start + 1; i < stones.length; i++) {
            int gap = stones[i] - stones[start];
            if (gap >= jumps - 1 && gap <= jumps + 1) {
                if (helper(stones, i, gap)) {
                    return true;
                }
            }
        }
        // return if or not reach the last index.
        return start == stones.length - 1;
    }

    // Memorization
    public boolean canCrossII(int[] stones) {
        int[][] memo = new int[stones.length][stones.length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return helperI(stones, 0, 0, memo) == 1;
    }

    private int helperI(int[] stones, int start, int jumps, int[][] memo) {
        if (memo[start][jumps] >= 0) {
            return memo[start][jumps];
        }
        for (int i = start + 1; i < stones.length; i++) {
            int gap = stones[i] - stones[start];
            if (gap >= jumps - 1 && gap <= jumps + 1) {
                if (helperI(stones, i, gap, memo) == 1) {
                    memo[start][gap] = 1;
                    return 1;
                }
            }
        }
        memo[start][jumps] = (start == stones.length - 1) ? 1 : 0;
        return memo[start][jumps];
    }

    // DP.
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i : stones) {
            map.put(i, new HashSet<>());
        }
        map.get(0).add(0);
        for (int i = 0; i < stones.length; i++) {
            for (int j : map.get(stones[i])) {
                for (int step = j - 1; step <= j + 1; step++) {
                    if (step > 0 && map.containsKey(stones[i] + step)) {
                        map.get(stones[i] + step).add(step);
                    }
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() > 0;
    }

    // 2D DP.
    public boolean canCrossIII(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n+1];
        dp[0][1] = true;
        for(int i =1 ; i < n ; i++) {
            for(int j = 0; j < i ; j++) {
                int diff = stones[i] - stones[j];
                if(diff < 0 || diff > n || !dp[j][diff]) {
                    continue;
                }
                dp[i][diff] = true;
                if(i == n-1) {
                    return true;
                }
                dp[i][diff-1] = dp[i][diff-1] || diff> 0;
                dp[i][diff+1] = dp[i][diff+1] || diff< n;
            }
        }
        return false;
    }
}