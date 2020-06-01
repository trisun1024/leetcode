class Solution {

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

}