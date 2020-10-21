import java.util.*;

class SplitArrayWithEqualSum {

    // Prefix Array + HashSet. Time = O(N^2); Space = O(N);
    public boolean splitArray(int[] nums) {
        if (nums.length < 7) {
            return false;
        }
        // build prefix array
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int j = 3; j < nums.length - 3; j++) {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (sum[i - 1] == sum[j - 1] - sum[i]) {
                    set.add(sum[i - 1]);
                }
            }
            for (int k = j + 2; k < nums.length - 1; k++) {
                if (sum[nums.length - 1] - sum[k] == sum[k - 1] - sum[j] && set.contains(sum[k - 1] - sum[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    // HashMap.
    public boolean splitArrayI(int[] nums) {
        if (nums == null || nums.length < 7) {
            return false;
        }
        int n = nums.length;
        int[] presum = new int[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + nums[i];
            if (!map.containsKey(presum[i + 1])) {
                map.put(presum[i + 1], i);
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            int sum = presum[n] - presum[i + 1];
            if (map.containsKey(sum)) {
                if (search(presum, map.get(sum) + 2, i - 1, sum)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(int[] presum, int start, int end, int sum) {
        if (start + 2 > end) {
            return false;
        }
        for (int i = start; i <= end; i++) {
            if (presum[end + 1] - presum[i + 1] == sum && presum[i] - presum[start] == sum) {
                return true;
            }
        }
        return false;
    }
}