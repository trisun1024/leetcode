class Solution {

    // DFS
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length == 0) {
            return false;
        }
        int tot = 0;
        for (int i : nums) {
            tot += i;
        }
        if (tot % k != 0) {
            return false;
        }
        int target = tot / k;
        boolean[] visited = new boolean[nums.length];
        return helper(nums, k, 0, 0, target, visited);
    }

    private boolean helper(int[] nums, int k, int index, int curSum, int target, boolean[] visited) {
        if (target == curSum) {
            return helper(nums, k - 1, 0, 0, target, visited);
        }
        if (k == 1) {
            return true;
        }
        for (int i = index; i < nums.length; i++) {
            if (!visited[i] && nums[i] <= target - curSum) {
                visited[i] = true;
                if (helper(nums, k, i + 1, curSum + nums[i], target, visited)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}