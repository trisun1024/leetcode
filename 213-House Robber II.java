
class HouseRobberII {

    // DP. Time = O(N); Space = O(N);
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int L = nums.length;
        int[] take = new int[L];
        int[] skip = new int[L];
        take[0] = nums[0];
        for (int i = 1; i < L - 1; i++) {
            take[i] = skip[i - 1] + nums[i];
            skip[i] = Math.max(take[i - 1], skip[i - 1]);
        }
        int result = Math.max(take[L - 2], skip[L - 2]);
        take[1] = nums[1];
        skip[1] = 0;
        for (int i = 2; i < L; i++) {
            take[i] = skip[i - 1] + nums[i];
            skip[i] = Math.max(take[i - 1], skip[i - 1]);
        }
        result = Math.max(result, take[L - 1]);
        result = Math.max(result, skip[L - 1]);
        return result;
    }

    // DFS
    public int robII(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    private int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }
}