import java.util.*;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0) {
            return res;
        }
        List<Integer> cur = new ArrayList<>();
        helper(n, k, 0, 1, cur, res);
        return res;
    }

    private void helper(int n, int k, int index, int start, List<Integer> cur, List<List<Integer>> res) {
        if (index == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i <= n; i++) {
            cur.add(i);
            helper(n, k, index + 1, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }

    // still backtracking, combine the k and index
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        if (n <= 0 || k <= 0)
            return results;

        backtracking(n, k, 1, results, new ArrayList());
        return results;
    }

    private void backtracking(int n, int k, int start, List<List<Integer>> results, List<Integer> temp) {
        if (k == 0) {
            results.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i <= n - k + 1; i++) {
            temp.add(i);
            backtracking(n, k - 1, i + 1, results, temp);
            temp.remove(temp.size() - 1);
        }
    }
}