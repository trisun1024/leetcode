class SortTransformedArray {

    // Time = O(N);
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] out = new int[n];
        // base case when a = 0
        if (a == 0) {
            if (b < 0) {
                for (int i = 0; i < n; i++) {
                    out[n - i - 1] = nums[i] * b + c;
                }
            } else {
                for (int i = 0; i < n; i++) {
                    out[i] = nums[i] * b + c;
                }
            }
            return out;
        }
        // binary search find initial left and right pointers
        int target = -1 * b / 2 / a;
        int l = 0;
        int r = nums.length - 1;
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid;
            } else {
                r = mid;
            }
        }
        // expand to all numbers 
        if (a < 0) {
            int index = n - 1;
            while (l >= 0 && r < n) {
                if (nums[l] * nums[l] * a + nums[l] * b + c > nums[r] * nums[r] * a + nums[r] * b + c) {
                    out[index] = nums[l] * nums[l] * a + nums[l] * b + c;
                    l--;
                } else {
                    out[index] = nums[r] * nums[r] * a + nums[r] * b + c;
                    r++;
                }
                index--;
            }
            while (l >= 0) {
                out[index] = nums[l] * nums[l] * a + nums[l] * b + c;
                l--;
                index--;
            }
            while (r < n) {
                out[index] = nums[r] * nums[r] * a + nums[r] * b + c;
                r++;
                index--;
            }
        } else {
            int index = 0;
            while (l >= 0 && r < n) {
                if (nums[l] * nums[l] * a + nums[l] * b + c < nums[r] * nums[r] * a + nums[r] * b + c) {
                    out[index] = nums[l] * nums[l] * a + nums[l] * b + c;
                    l--;
                } else {
                    out[index] = nums[r] * nums[r] * a + nums[r] * b + c;
                    r++;
                }
                index++;
            }
            while (l >= 0) {
                out[index] = nums[l] * nums[l] * a + nums[l] * b + c;
                l--;
                index++;
            }
            while (r < n) {
                out[index] = nums[r] * nums[r] * a + nums[r] * b + c;
                r++;
                index++;
            }
        }
        return out;
    }
}