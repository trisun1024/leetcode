class Solution {

    // DP Time = O(N); Space = O(N);
    /*
     * DP idea is to find all leftmax and rightmax for each index points
     * eg. 
     * input = 4, 1, 3, 4, 5, 2, 6
     * lMax  = 4, 4, 4, 4, 5, 5, 6
     * rMax  = 6, 6, 6, 6, 6, 6, 6
     * for each index i 
     * sum += (min(lmax[i], rmax[i]) - input[i]);
     */

    public int trapDP(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return max;
    }

    // Two Pointers
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        int lmax = height[left];
        int rmax = height[right];
        while (left < right) {
            if (height[left] <= height[right]) {
                max += Math.max(0, lmax - height[left]);
                lmax = Math.max(lmax, height[left]);
                left++;
            } else {
                max += Math.max(0, rmax - height[right]);
                rmax = Math.max(rmax, height[right]);
                right--;
            }
        }
        return max;
    }
}