import java.util.*;

class Solution {

    // return a list of all possible sequences
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        List<Integer> holder = new ArrayList<Integer>();
        findSequence(res, holder, 0, nums);
        return new ArrayList<>(res);
    }

    public void findSequence(Set<List<Integer>> res, List<Integer> holder, int index, int[] nums) {
        if (holder.size() >= 2) {
            res.add(new ArrayList<>(holder));
        }
        for (int i = index; i < nums.length; i++) {
            if (holder.size() == 0 || holder.get(holder.size() - 1) <= nums[i]) {
                holder.add(nums[i]);
                findSequence(res, holder, i + 1, nums);
                holder.remove(holder.size() - 1);
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