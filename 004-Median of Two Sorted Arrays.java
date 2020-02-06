import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            findMedianSortedArrays(nums2, nums1);
        } else {
            int leftMin = 0, leftMax = m, mid = (m + n + 1) / 2;
            while (leftMin <= leftMax) {
                int i = (leftMin + leftMax) / 2;
                int j = mid - 1;
                if (i < leftMax && nums2[j - 1] > nums1[i]) {
                    leftMin = i + 1; // i is too small, increase i
                } else if (i > leftMin && nums1[i - 1] > nums2[j]) {
                    leftMax = i - 1; // i is too big, decrease i
                } else { // i is perfect
                    int midLeft = 0;
                    if (i == 0) {
                        midLeft = nums2[j - 1];
                    } else if (j == 0) {
                        midLeft = nums1[i - 1];
                    } else {
                        midLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                    }
                    if ((m + n) % 2 == 1)
                        return midLeft;

                    int midRight = 0;
                    if (i == m) {
                        midRight = nums2[j];
                    } else if (j == n) {
                        midRight = nums1[i];
                    } else {
                        midRight = Math.max(nums2[j], nums1[i]);
                    }
                    return (midLeft + midRight) / 2.0;
                }
            }
            return 0.0;
        }
    }
}