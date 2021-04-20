class MedianOfTwoSortedArrays {

    // Recursion + Binary Search. Time = O(log(MIN(M,N)));
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 0) {
            return (kth(nums1, nums2, 0, 0, len / 2) + kth(nums1, nums2, 0, 0, len / 2 + 1)) / 2.0;
        } else {
            return (double) kth(nums1, nums2, 0, 0, len / 2 + 1);
        }
    }

    private int kth(int[] nums1, int[] nums2, int l1, int l2, int k) {
        if (l1 >= nums1.length) {
            return nums2[l2 + k - 1];
        }
        if (l2 >= nums2.length) {
            return nums1[l1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[l1], nums2[l2]);
        }
        int m1 = l1 + k / 2 - 1;
        int m2 = l2 + k / 2 - 1;
        int val1 = m1 >= nums1.length ? Integer.MAX_VALUE : nums1[m1];
        int val2 = m2 >= nums2.length ? Integer.MAX_VALUE : nums2[m2];
        if (val1 <= val2) {
            return kth(nums1, nums2, m1 + 1, l2, k - k / 2);
        } else {
            return kth(nums1, nums2, l1, m2 + 1, k - k / 2);
        }
    }
}