import java.util.*;

// My work 984ms
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 2)
            return res;

        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            for (int j = i + 1, k = nums.length - 1; j < k;) {
                if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                } else {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    if (!res.contains(temp))
                        res.add(temp);
                    for (j++, k--; j < k && nums[j] == nums[j - 1]; j++)
                        ;
                }
            }
        }
        return res;
    }

    // 30 ms
    public List<List<Integer>> threeSumII(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // to exclude the duplicated number
            twoSum(0 - nums[i], nums, i + 1, nums.length - 1, res);
        }
        return res;
    }

    private void twoSum(int target, int[] nums, int start, int end, List<List<Integer>> res) {
        int i = start, j = end;
        while (i < j) {
            List<Integer> subres = new ArrayList<Integer>();
            int sum = nums[i] + nums[j];
            if (sum == target) {
                subres.add(0 - target);
                subres.add(nums[i]);
                subres.add(nums[j]);
                res.add(subres);
                do {
                    i++;
                } while (i < end && nums[i] == nums[i - 1]); // to exclude the duplicated number
                do {
                    j--;
                } while (j >= 0 && nums[j] == nums[j + 1]); // to exclude the duplicated number
            } else if (sum < target)
                i++;
            else
                j--;
        }
    }
}
