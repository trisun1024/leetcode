import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > target) {
                    right--;
                } else {
                    left++;
                }
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
            }
        }
        return res;
    }

    int min = Integer.MAX_VALUE, sum = 0;

    public int threeSumClosestII(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            getTwoSums(nums, i, target);
            if (min == 0) {
                return target;
            }
        }

        return sum;
    }

    private void getTwoSums(int[] nums, int i, int target) {
        int l = i + 1, r = nums.length - 1;

        while (l < r) {
            int s = nums[i] + nums[l] + nums[r];
            if (min > Math.abs(target - s)) {
                min = Math.abs(target - s);
                sum = s;
            }
            if (s == target) {
                min = 0;
                return;
            } else if (s < target) {
                l++;
            } else {
                r--;
            }
        }
    }
}