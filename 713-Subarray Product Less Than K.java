
class SubarrayProductLessThanK {

    // Binary Search. O(N*log(N));
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        double logk = Math.log(k);
        int n = nums.length;
        double[] prefix = new double[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + Math.log(nums[i]);
        }
        int count = 0;
        for (int i = 0; i < prefix.length; i++) {
            int l = i + 1;
            int r = prefix.length;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (prefix[m] < prefix[i] + logk - 1e-9) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            count += l - i - 1;
        }
        return count;
    }

    // Sliding Window. O(N);
    public int numSubarrayProductLessThanKI(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int prod = 1;
        int count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) {
                prod /= nums[left++];
            }
            count += right - left + 1;
        }
        return count;
    }
}