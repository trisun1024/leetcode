import java.util.*;
class Solution {

    // DFS
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(k, n, 1, cur, res);
        return res;
    }

    private void helper(int k, int n, int start, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == k && n == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i <= 9; i++) {
            cur.add(i);
            helper(k, n - i, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}