
class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // two pointer, copy from the end
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        // copy from the end,
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i--];
            } else {
                nums1[k] = nums2[j--];
            }
            k--;
        }
        // post-processing, if one is greater and equal than zero, then keep processing
        while (i >= 0) {
            nums1[k--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

}
