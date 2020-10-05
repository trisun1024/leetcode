import java.util.*;

class CombinationSum {
 
    // DFS. 
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, 0, target, cur, res);
        // helperI(candidates, 0, target, cur, res);
        return res;
    }
    
    private void helper(int[] candidates, int index, int remain, List<Integer> cur, List<List<Integer>> res) {
        if(remain < 0) {
            return ;
        }
        if(remain == 0) {
            res.add(new ArrayList<>(cur));
        }
        for(int i = index; i < candidates.length; i++) {
            cur.add(candidates[i]);
            helper(candidates, i, remain-candidates[i], cur, res);
            cur.remove(cur.size()-1);
        }
    }

    // Improve DFS. Early Stop. 
    private void helperI(int[] candidates, int index, int remain, List<Integer> cur , List<List<Integer>> res) {
        if(remain == 0) {
            res.add(new ArrayList<>(cur));
        }
        for(int i = index; i < candidates.length; i++) {
            if(remain - candidates[i] < 0) {
                continue;
            }
            cur.add(candidates[i]);
            helper(candidates, i, remain-candidates[i], cur, res);
            cur.remove(cur.size()-1);
        }
    }
}