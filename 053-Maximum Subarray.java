class MaximumSubarray {

    // DP. Time = O(N); Space = O(N);
    public int maxSubArrayI(int[] nums) {
        // M[i] represents [the range of 0th to ith element] the largest sum of subarray
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // Greedy. Time = O(N); Space = O(1);
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(cur + nums[i], nums[i]);
            max = Math.max(cur, max);
        }
        return max;
    }

    // Divide and Conqure.
    public int maxSubArrayII(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int left, int right) {
        if (left >= right) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        int leftSum = helper(nums, left, mid);
        int rightSum = helper(nums, mid + 1, right);
        int crossSum = crossSum(nums, left, right, mid);
        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    private int crossSum(int[] nums, int left, int right, int mid) {
        if (left >= right) {
            return nums[left];
        }
        int leftSubSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = mid; i > left - 1; i--) {
            curSum += nums[i];
            leftSubSum = Math.max(leftSubSum, curSum);
        }
        int rightSubSum = Integer.MIN_VALUE;
        curSum = 0;
        for (int i = mid + 1; i < right + 1; i++) {
            curSum += nums[i];
            rightSubSum = Math.max(rightSubSum, curSum);
        }
        return leftSubSum + rightSubSum;
    }

    public int maxSubArrayIV(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0)
                nums[i] += nums[i - 1];
            if (nums[i] > max)
                max = nums[i];
        }
        return max;
    }

    // return index
    public int[] maxSubArrayIII(int[] nums) {
        int curLeft = 0, curRight = 0;
        int globalLeft = 0, globalRight = 0;
        int max = nums[0], cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (cur < 0) {
                cur = nums[curRight];
                curLeft = curRight;
            } else {
                cur += nums[i];
            }
            curRight++;
            if (max < cur) {
                globalLeft = curLeft;
                globalRight = curRight;
            }
        }
        return new int[] { globalLeft, globalRight };
    }
}