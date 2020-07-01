class MinimumSizeSubarraySum {

    // Two Pointers, Sliding window Time = O(N)
    public int minSubArrayLen(int s, int[] nums) {
        int minLen = Integer.MAX_VALUE; // minimum length we are looking for
        int left = 0; // left point
        int right = 0; // right point
        int cur = 0; // current sum
        while (right < nums.length) {
            cur += nums[right]; // add current right position number into sum
            // if sum greater or equal than s, more the left pointer find the smallest
            // length
            while (cur >= s) {
                minLen = Math.min(minLen, right - left + 1);
                cur -= nums[left++];
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // Binary Search Time = O(N*log(N))
    public int minSubArrayLenII(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= nums.length; i++) {
            int toFind = s + nums[i - 1];
            int bound = binarySearch(prefix, toFind);
            if (bound != prefix[prefix.length - 1]) {
                res = Math.min(res, (bound - prefix[0] + i - 1));
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

}