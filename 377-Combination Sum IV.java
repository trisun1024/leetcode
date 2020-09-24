
import java.util.*;

class CombinationSumIV {

    // DP + Memorization. Time = O(N*K); Space = O(K);
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        helper(nums, target, dp);
        return dp[target];
    }

    private int helper(int[] nums, int target, int[] dp) {
        if (dp[target] != -1) {
            return dp[target];
        }
        int count = 0;
        for (int n : nums) {
            if (n > target) {
                continue;
            }
            count += helper(nums, target - n, dp);
        }
        dp[target] = count;
        return count;
    }

    // DP. Time = O(N*K);
    public int combinationSum4I(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int n : nums) {
                if (i >= n) {
                    dp[i] = dp[i] + dp[i - n];
                }
            }
        }
        return dp[target];
    }
}