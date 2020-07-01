import java.util.*;

class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>(res);
        }
        Arrays.sort(candidates);
        dfs(candidates, 0, target, cur, res);
        return res;
    }

    private void dfs(int[] candidates, int start, int target, List<Integer> cur, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (target - candidates[i] < 0)
                break;
            cur.add(candidates[i]);
            dfs(candidates, i + 1, target - candidates[i], cur, res);
            cur.remove(cur.size() - 1);
        }
    }

}