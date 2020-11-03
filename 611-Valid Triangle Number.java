import java.util.*;

class ValidTriangleNumber {

    // Brute Force. Time = O(N^3);
    public int triangleNumberI(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] > nums[k] && nums[i] + nums[k] > nums[j] && nums[j] + nums[k] > nums[i])
                        count++;
                }
            }
        }
        return count;
    }

    // Binary Search. Time = O(N^2*log(N));
    public int triangleNumberII(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) {
                k = binarySearch(nums, k, nums.length - 1, nums[i] + nums[j]);
                count += k - j - 1;
            }
        }
        return count;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right && right < nums.length) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // Linear Scan. Time = O(N^2);
    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for(int i = 0 ; i < nums.length-2; i++) {
            int k = i+2;
            for(int j = i+1;j < nums.length-1 && nums[i] != 0 ; j++)  {
                while(k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                count += k-j-1;
            }
        }
        return count;
    }
}