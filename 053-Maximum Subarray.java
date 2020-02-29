class Solution {

    // DP
    // M[i] represents [the range of 0th to ith element] the largest sum of subarray
    // T = O(N) S = O(1)
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(cur + nums[i], nums[i]);
            res = Math.max(cur, res);
        }
        return res;
    }

    // return index
    public int[] maxSubArrayI(int[] nums) {
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