class FindFirstLast {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];

        if (nums == null || nums.length == 0)
            return new int[] { -1, -1 };

        // left
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target > nums[mid])
                l = mid + 1;
            else
                r = mid;
        }

        if (target == nums[l])
            res[0] = l;
        else
            res[0] = -1;

        // right
        r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2 + 1;
            if (target < nums[mid])
                r = mid - 1;
            else
                l = mid;
        }
        if (target == nums[r])
            res[1] = r;
        else
            res[1] = -1;

        return res;
    }
}