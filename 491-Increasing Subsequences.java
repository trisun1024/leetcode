import java.util.*;

class IncreasingSubsequences {

    // return a list of all possible sequences14
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();
        helper(nums, 0, cur, res);
        return new ArrayList<>(res);
    }

    public void helper(int[] nums, int index, List<Integer> cur, Set<List<Integer>> res) {
        if (index > nums.length) {
            return;
        }
        if (cur.size() > 1) {
            res.add(new ArrayList<>(cur));
        }
        for (int i = index; i < nums.length; i++) {
            if (cur.size() == 0 || cur.get(cur.size() - 1) <= nums[i]) {
                cur.add(nums[i]);
                helper(nums, i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    // return number of all possible sequences
    // dfs
    public int countSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> cur = new ArrayList<>();
        dfs(nums, 0, cur, res);
        return res.size();
    }

    private void dfs(int[] nums, int index, List<Integer> cur, Set<List<Integer>> res) {
        if (cur.size() >= 1) {
            res.add(new ArrayList<>(cur));
        }
        for (int i = index; i < nums.length; i++) {
            if (cur.size() == 0 || cur.get(cur.size() - 1) <= nums[i]) {
                cur.add(nums[i]);
                dfs(nums, i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    // dp
    public int countSubsequencesII(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return n;
        }
        int[] count = new int[100000];
        for (int i = 0; i < n; i++) {
            for (int j = a[i] - 1; j >= 0; j--) {
                count[a[i]] += count[j];
            }
            count[a[i]]++;
        }
        int res = 0;
        for (int i : count) {
            res += i;
        }
        return res;
    }
}