class Solution {
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

