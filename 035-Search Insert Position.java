class Solution {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
            if (nums[0] > target)
                return 0;
            if (nums[i] > target)
                return i;
        }
        return nums.length;
    }
}