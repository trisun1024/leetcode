class SingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1) {
                mid--;
            }
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}