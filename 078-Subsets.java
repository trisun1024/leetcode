import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(nums, 0, cur, res);
        return res;
    }

    private void helper(int[] nums, int index, List<Integer> cur, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        helper(nums, index + 1, cur, res);
        cur.add(nums[index]);
        helper(nums, index + 1, cur, res);
        cur.remove(cur.size() - 1);
    }
}