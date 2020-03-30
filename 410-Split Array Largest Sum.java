class Solution {

    // 2D DP Time = O(N^2*M) Space = O(N*M)
    public int splitArray(int[] nums, int m) {
        int len = nums.length;
        // use prefix sum array to accelerate fast access the sum of subarray
        // size 0 - len+1
        int[] prefix = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        // build 2D dp array, columns are the position of the array to split, rows are
        // the number of array split
        int[][] dp = new int[len + 1][m + 1];
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], prefix[i] - prefix[k]));
                }
            }
        }
        return dp[len][m];
    }

    // optimized space O(N)
    public int splitArrayII(int[] nums, int m) {
        int n = nums.length;
        int[] pref_sum = new int[n];
        // use two 1D array
        // dp[j][k] represents the minimum of largest subarray sum with k cuts up to
        // i-th element
        // use two 1D arrays to optimize space complexity because the column which
        // is currently being updated will only need the information of the previous
        // column
        int[] prev = new int[n];
        int[] cur = new int[n];
        for (int i = 0; i < n; i++) {
            pref_sum[i] = i == 0 ? nums[0] : pref_sum[i - 1] + nums[i];
            prev[i] = pref_sum[i];
        }
        for (int k = 1; k < m; k++) {
            for (int j = k; j < n; j++) {
                cur[j] = Integer.MAX_VALUE;
                for (int i = j - 1; i >= 0; i--) {
                    if (pref_sum[j] - pref_sum[i] > cur[j]) {
                        break;
                    }
                    int tmp = Math.max(prev[i], pref_sum[j] - pref_sum[i]);
                    cur[j] = Math.min(cur[j], tmp);
                }
            }
            for (int i = 0; i < n; i++) {
                prev[i] = cur[i];
                cur[i] = 0;
            }
        }
        return prev[n - 1];
    }

    // binary search + greedy
    // whole idea is group theory
    public int splitArrayIII(int[] nums, int m) {
        long left = findMax(nums);
        long right = findSum(nums);
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (findKGroups(nums, mid) > m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return (int) left;
    }

    private long findMax(int[] nums) {
        long max = 0;
        for (int i : nums) {
            max = Math.max(max, i);
        }
        return max;
    }

    private long findSum(int[] nums) {
        long sum = 0;
        for (int i : nums) {
            sum += i;
        }
        return sum;
    }

    private int findKGroups(int[] nums, long max) {
        int k = 0;
        long cur = 0;
        for (int i : nums) {
            if (cur + i <= max) {
                cur += i;
            } else {
                cur = i;
                k++;
            }
        }
        if (cur != 0) {
            k++;
        }
        return k;
    }
}