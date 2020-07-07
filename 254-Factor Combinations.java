import java.util.*;

class FactorCombinations {

    // DFS. Time = O(N*H);
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 1) {
            return res;
        }
        List<Integer> cur = new ArrayList<>();
        helper(n, 2, cur, res);
        return res;
    }

    private void helper(int n, int start, List<Integer> cur, List<List<Integer>> res) {
        if (n <= 1) {
            if (cur.size() > 1)
                res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                cur.add(i);
                helper(n / i, i, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
}