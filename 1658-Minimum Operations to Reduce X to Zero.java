
class MinimumOperationsToReduceXtoZero {

    // Two Pointers. Time = O(N);
    public int minOperations(int[] nums, int x) {
        int tot = 0;
        for (int n : nums) {
            tot += n;
        }
        int n = nums.length;
        int max = -1;
        int l = 0;
        int cur = 0;
        for (int r = 0; r < n; r++) {
            cur += nums[r];
            while (cur > tot - x && l <= r) {
                cur -= nums[l];
                l += 1;
            }
            if (cur == tot - x) {
                max = Math.max(max, r - l + 1);
            }
        }
        return max != -1 ? n - max : -1;
    }

    // Two Pointers Direct. Time = O(N);
    public int minOperationsI(int[] nums, int x) {
        int current = 0;
        for (int num : nums) {
            current += num;
        }
        int n = nums.length;
        int mini = Integer.MAX_VALUE;
        int left = 0;

        for (int right = 0; right < n; right++) {
            // sum([0,..,left) + (right,...,n-1]) = x
            current -= nums[right];
            // if smaller, move `left` to left
            while (current < x && left <= right) {
                current += nums[left];
                left += 1;
            }
            // check if equal
            if (current == x) {
                mini = Math.min(mini, (n - 1 - right) + left);
            }
        }
        return mini != Integer.MAX_VALUE ? mini : -1;
    }
}