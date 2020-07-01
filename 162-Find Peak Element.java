class FindPeakElement {

    // Linear Scan. Time = O(N);
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    // Binary Search Recursion. Time = O(log(N)); Space = O(log(N));
    public int findPeakElementII(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    private int search(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] > nums[mid + 1]) {
            return search(nums, 1, mid);
        }
        return search(nums, mid + 1, right);
    }

    // Binary Search Iteration. Time = O(log(N)); Space = O(1)
    public int findPeakElementIII(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[m + 1]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}