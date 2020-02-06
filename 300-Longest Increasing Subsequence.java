class Solution {
    // Time O(N^2) Space O(N)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] longest = new int[nums.length];
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            longest[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    longest[i] = Math.max(longest[j] + 1, longest[i]);
                }
            }
            res = Math.max(longest[i], res);
        }
        return res;
    }
}