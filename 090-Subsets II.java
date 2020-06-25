import java.util.*;

class Solution {
    // Given a collection of integers that might contain duplicates, nums, return
    // all possible subsets (the power set).

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, cur, res);
        return res;
    }

    private void dfs(int[] nums, int index, List<Integer> cur, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        cur.add(nums[index]);
        dfs(nums, index + 1, cur, res);
        cur.remove(cur.size() - 1);
        // skip if the next nums[index+1] == nums[index]
        while (index < nums.length - 1 && nums[index] == nums[index + 1]) {
            index++;
        }
        dfs(nums, index + 1, cur, res);
    }

}
