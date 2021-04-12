import java.util.*;

class BeautifulArrangementII {

    // Brute Force.
    public int[] constructArrayI(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        for (List<Integer> candidate : permutation(nums)) {
            if (numUniqueDiff(candidate) == k) {
                int[] ans = new int[n];
                for (int i = 0; i < n; i++) {
                    ans[i] = candidate.get(i);
                }
                return ans;
            }
        }
        return null;
    }

    private List<List<Integer>> permutation(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, 0, res);
        return res;
    }

    private void helper(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length) {
            List<Integer> cur = new ArrayList<>();
            for (int x : nums) {
                cur.add(x);
            }
            res.add(cur);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            helper(nums, index + 1, res);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int numUniqueDiff(List<Integer> arr) {
        boolean[] seen = new boolean[arr.size()];
        int ans = 0;
        for (int i = 0; i < arr.size() - 1; i++) {
            int diff = Math.abs(arr.get(i) - arr.get(i + 1));
            if (!seen[diff]) {
                ans++;
                seen[diff] = true;
            }
        }
        return ans;
    }

    // Construction. Time = O(N); Space = O(N);
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int c = 0;
        for (int v = 1; v < n - k; v++) {
            ans[c++] = v;
        }
        for (int i = 0; i <= k; i++) {
            ans[c++] = (i % 2 == 0) ? (n - k + i / 2) : (n - i / 2);
        }
        return ans;
    }

}