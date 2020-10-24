import java.util.*;

class OneThreeTwoPattern {

    // Brute Force. Time = O(N^3);
    public boolean find132patternI(int[] nums) {
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[k] > nums[i] && nums[j] > nums[k])
                        return true;
                }
            }
        }
        return false;
    }

    // Better Brute Force.
    public boolean find132patternII(int[] nums) {
        int min_i = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length - 1; j++) {
            min_i = Math.min(min_i, nums[j]);
            for (int k = j + 1; k < nums.length; k++) {
                if (nums[k] < nums[j] && min_i < nums[k])
                    return true;
            }
        }
        return false;
    }

    // Stack. Time = O(N);
    public boolean find132patternIII(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > min[i]) {
                while (!stack.isEmpty() && stack.peekFirst() <= min[i]) {
                    stack.pollFirst();
                }
                if (!stack.isEmpty() && stack.peekFirst() < nums[i]) {
                    return true;
                }
                stack.offerFirst(nums[i]);
            }
        }
        return false;
    }

    // Use Array as Stack. Time = O(N);
    public boolean find132patternIIII(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        // Stack.
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        for (int j = nums.length - 1, k = nums.length; j >= 0; j--) {
            if (nums[j] > min[j]) {
                while (k < nums.length && nums[k] <= min[j]) {
                    k++;
                }
                if (k < nums.length && nums[k] < nums[j]) {
                    return true;
                }
                nums[--k] = nums[j];
            }
        }
        return false;
    }

    // Binary Search. Time = O(N*log(N));
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        for (int j = nums.length - 1, k = nums.length; j >= 0; j--) {
            if (nums[j] > min[j]) {
                k = Arrays.binarySearch(nums, k, nums.length, min[j] + 1);
                if (k < 0) {
                    k = -1 - k;
                }
                if (k < nums.length && nums[k] < nums[j]) {
                    return true;
                }
                nums[--k] = nums[j];
            }
        }
        return false;
    }
}