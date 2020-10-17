class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int cur = nums[0];
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (i == 0 || nums[i - 1] == 0) {
                    cur = 1;
                } else {
                    cur++;
                }
                max = Math.max(cur, max);
            }
        }
        return max;
    }
}