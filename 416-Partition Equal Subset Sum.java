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

    // DP 2D Array. Time = O(M*N); Space = O(N^2);
    public boolean canPartitionII(int[] nums) {
        int totalSum = 0;
        // find sum of all array elements
        for (int num : nums) {
            totalSum += num;
        }
        // if totalSum is odd,it cannot be partitioned into equal sum subset
        if (totalSum % 2 != 0) {
            return false;
        }
        int subSetSum = totalSum / 2;
        int n = nums.length;
        boolean dp[][] = new boolean[n + 1][subSetSum + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            int curr = nums[i - 1];
            for (int j = 0; j <= subSetSum; j++) {
                if (j < curr) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || (dp[i - 1][j - curr]);
                }
            }
        }
        return dp[n][subSetSum];
    }

    // DP 1D Array. Time = O(M*N); Space = O(N);
    public boolean canPartitionI(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        int totalSum = 0;
        // find sum of all array elements
        for (int num : nums) {
            totalSum += num;
        }
        // if totalSum is odd,it cannot be partitioned into equal sum subset
        if (totalSum % 2 != 0) {
            return false;
        }
        int subSetSum = totalSum / 2;
        boolean dp[] = new boolean[subSetSum + 1];
        dp[0] = true;
        for (int curr : nums) {
            for (int j = subSetSum; j >= curr; j--) {
                dp[j] |= dp[j - curr];
            }
        }
        return dp[subSetSum];
    }
}