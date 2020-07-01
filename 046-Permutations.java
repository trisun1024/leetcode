import java.util.*;

class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, 0, res);
        return res;
    }

    private void helper(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            List<Integer> tmp = new ArrayList<>();
            for (int i : nums) {
                tmp.add(i);
            }
            res.add(tmp);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            helper(nums, index + 1, res);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}