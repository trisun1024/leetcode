// dynamic programming
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur_res = new ArrayList<>();
        Arrays.sort(candidates); // for possible early stop
        backtrack(candidates, 0, target, cur_res, result);
        return result;
    }

    private void backtrack(int[] nums, int start, int remain, List<Integer> cur_res, List<List<Integer>> result) {
        if (remain < 0) // early stop
            return;
        else if (remain == 0)
            result.add(new ArrayList<>(cur_res));
        else {
            for (int i = start; i < nums.length; i++) {
                cur_res.add(nums[i]);
                backtrack(nums, i, remain - nums[i], cur_res, result);
                cur_res.remove(cur_res.size() - 1);
            }
        }
    }
}