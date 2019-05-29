class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int res = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > target)
                    right--;
                else
                    left++;
                if (Math.abs(sum - target) < Math.abs(res - target))
                    res = sum;
            }
        }
        return res;
    }
}