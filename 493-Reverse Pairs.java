
class ReversePairs {

    // Brute Force.
    public int reversePairsI(int[] nums) {
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j] * 2) {
                    c++;
                }
            }
        }
        return c;
    }

    // Merge Sort.
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int count = 0;
        int mid = left + (right - left) / 2;
        count += mergeSort(nums, left, mid);
        count += mergeSort(nums, mid + 1, right);
        count += merge(nums, left, mid, right);
        return count;
    }

    private int merge(int[] nums, int left, int mid, int right) {
        int count = 0, l1 = left, l2 = mid + 1, idx = 0;
        while (l1 <= mid && l2 <= right) {
            if ((long) nums[l1] > (long) 2 * nums[l2]) {
                count += mid - l1 + 1;
                l2++;
            } else {
                l1++;
            }
        }
        l1 = left;
        l2 = mid + 1;
        int[] copy = new int[right - left + 1];
        while (l1 <= mid && l2 <= right) {
            if (nums[l1] > nums[l2]) {
                copy[idx++] = nums[l2++];
            } else {
                copy[idx++] = nums[l1++];
            }
        }
        while (l1 <= mid) {
            copy[idx++] = nums[l1++];
        }
        while (l2 <= right) {
            copy[idx++] = nums[l2++];
        }
        System.arraycopy(copy, 0, nums, left, right - left + 1);
        return count;
    }
}