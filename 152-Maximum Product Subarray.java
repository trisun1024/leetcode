class Solution {
    public int maxProduct(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }
        int res = nums[0];
        int min = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++) {
            // save previous maximum value
            int prevMax = max;
            // update max value
            max = Math.max(Math.max(max*nums[i], min*nums[i]), nums[i]);
            // update min value
            min = Math.min(Math.min(prevMax*nums[i], min*nums[i]), nums[i]);
            // update res value
            res = Math.max(res, max);
        }
        return res;
    }
}