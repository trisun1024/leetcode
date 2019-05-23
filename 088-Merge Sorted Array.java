
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[k--] = (nums1[i] < nums2[j]) ? nums2[j--] : nums1[i--];
        }
        System.arraycopy(nums2, 0, nums1, 0, j + 1);
    }
}

/* 
// 1 Line code 
// Based on previous solution, 
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
       while(n>0) nums1[m+n-1] = (m==0||nums2[n-1] > nums1[m-1]) ? nums2[--n] : nums1[--m];      
    }
}
*/