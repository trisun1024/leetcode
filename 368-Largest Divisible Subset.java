import java.util.*;

class LargestDivisibleSubset {

    // DFS
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Map<Integer, List<Integer>> out = new HashMap<>();
        List<Integer> cur = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, cur, out);
        int max = 0;
        for(Integer i: out.keySet()) {
            max = Math.max(max, i);
        }
        return out.get(max);
    }
    private void helper(int[] nums, int index, List<Integer> cur, Map<Integer, List<Integer>> out) {
        if(index== nums.length) {
            out.put(cur.size() , new ArrayList<>(cur));
            return ;
        }
        helper(nums, index+1, cur, out);
        if(isValid(nums, index, cur)) {
            cur.add(nums[index]);
            helper(nums, index+1, cur, out);
            cur.remove(cur.size()-1);
        }
    }
    private boolean isValid(int[] nums, int index, List<Integer> cur) {
        if(cur.size()==0) {
            return true;
        }
        for(int i = 0; i < cur.size(); i++) {
            if(nums[index] % cur.get(i) != 0) {
                return false;
            }
        }
        return true;
    }

    // DP
    public List<Integer> largestDivisibleSubsetII(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return res;
        }
        Arrays.sort(nums);
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                int tmp = dp[j] + 1;
                if (nums[i] % nums[j] == 0 && tmp > dp[i]) {
                    dp[i] = tmp;
                }
            }
        }
        int prevMax = 0;
        for (int i = 0; i < n; i++) {
            if (prevMax < dp[i]) {
                prevMax = dp[i];
                res.add(nums[i]);
            }
        }
        return res;
    }

    // DP with index; Time = O(N^2); Space = O(N)
    public List<Integer> largestDivisibleSubsetIII(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return res;
        }
        int[] dp = new int[n];
        int[] idx = new int[n];
        int start = Integer.MIN_VALUE;
        int max = 0;
        Arrays.fill(idx, -1);
        Arrays.sort(nums);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (dp[j] > dp[i] && nums[j] % nums[i] == 0) {
                    dp[i] = dp[j];
                    idx[i] = j;
                }
            }
            dp[i]++;
            if (dp[i] > max) {
                max = dp[i];
                start = i;
            }
        }
        while (max > 0) {
            res.add(nums[start]);
            start = idx[start];
            max--;
        }
        return res;
    }
}