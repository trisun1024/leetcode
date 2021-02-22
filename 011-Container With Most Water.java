class ContainMostWater {
    // Brute Force. Time = O(N^2);
    public int maxAreaI(int[] height) {
        int max = 0;
        int n = height.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return max;
    }

    // Two Pointers. Time = O(N);
    public int maxArea(int[] height) {
        int area = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            area = Math.max(area, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return area;
    }
}
