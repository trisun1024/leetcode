class SearchInsertPosition {

    // loop for all Time = O(N)
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
            if (nums[0] > target)
                return 0;
            if (nums[i] > target)
                return i;
        }
        return nums.length;
    }

    // binary search
    public int searchInsertII(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < target) {
                l = m;
            } else {
                r = m;
            }
        }
        // we stop early and left two pointers, left and right
        if (nums[l] >= target && nums[r] > target) {
            return l;
        } else if (nums[l] < target && nums[r] < target) {
            return r + 1;
        } else {
            return r;
        }
    }
}