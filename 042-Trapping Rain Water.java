// Stack Solution 6ms
class Solution {
    public int trap(int[] height) {
        if(height.length==0) return 0;
        Stack<Integer> stack = new Stack<>();
        int i =0, maxWater = 0, maxBarWater=0;
        while(i<height.length) {
            if(stack.isEmpty() || height[i]<=height[stack.peek()]) {
                stack.push(i++);
            } else {
                int bar = stack.pop();
                maxBarWater = stack.isEmpty() ? 0: (Math.min(height[stack.peek()],height[i])-height[bar])*(i-stack.peek()-1);
                maxWater += maxBarWater;
            }
        }
        return maxWater;
    }
}

// Min(left boundary, right boundary) * length - any smaller bar in between
class Solution2 {
    public int trap(int[] A) {
        int res = 0, left = 0, right = A.length - 1, prevHeight = 0;

        while (left < right) {
            int height = (A[left] < A[right] ? A[left] : A[right]);
            if (height > prevHeight) {
                res -= prevHeight;
                res += (right - left - 1) * (height - prevHeight);
                prevHeight = height;
            } else {
                res -= height;
            }
            if (A[left] <= A[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}