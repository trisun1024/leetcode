class PartitionEqualSubsetSum {

    // DFS
    public boolean canPartition(int[] nums) {
        int tot = 0;
        for (int i : nums) {
            tot += i;
        }
        if (tot % 2 != 0) {
            return false;
        }
        boolean[] visited = new boolean[nums.length];
        return helper(nums, 0, tot / 2, visited);
    }

    private boolean helper(int[] nums, int index, int target, boolean[] visited) {
        if (target == 0) {
            return true;
        }
        for (int i = index; i < nums.length; i++) {
            if (!visited[i] && target >= nums[index]) {
                visited[i] = true;
                if (helper(nums, i + 1, target - nums[index], visited)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    // DP
    public boolean canPartitionI(int[] nums) {
        // check edge case
        if (nums == null || nums.length == 0) {
            return true;
        }
        // preprocess
        int volumn = 0;
        for (int num : nums) {
            volumn += num;
        }
        if (volumn % 2 != 0) {
            return false;
        }
        volumn /= 2;
        // dp def
        boolean[] dp = new boolean[volumn + 1];
        // dp init
        dp[0] = true;
        // dp transition
        for (int i = 1; i <= nums.length; i++) {
            for (int j = volumn; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i - 1]];
            }
        }
        return dp[volumn];
    }
}