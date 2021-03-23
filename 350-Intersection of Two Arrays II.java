import java.util.*;

class IntersectionOfTwoArraysII {

    // Two Pointers & in-place. Time = (N*log(N)); Space = O(1);
    public int[] intersect(int[] nums1, int[] nums2) {
        // sort both array
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // i for nums1, j for nums2, k for finding
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < nums1.length && j < nums2.length) {
            // 1. i less than j then i++;
            // 2. j less than i then j++;
            // 3. equal find match then copy i to k then i,j,k all ++;
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                nums1[k] = nums1[i];
                k++;
                i++;
                j++;
            }

        }
        return Arrays.copyOfRange(nums1, 0, k);
    }
}