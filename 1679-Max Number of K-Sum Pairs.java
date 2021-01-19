import java.util.*;

class MaxNumberKSumPairs {

    // Brute Force. Time = O(N^2); Space = O(1);
    public int maxOperationsI(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == 0) {
                    continue;
                }
                if (nums[i] + nums[j] == k) {
                    nums[i] = nums[j] = 0;
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    // HashMap + Single Pass. Time = O(N); Space = O(N);
    public int maxOperationsII(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int complement = k - current;
            if (map.getOrDefault(complement, 0) > 0) {
                // remove complement from the map
                map.put(complement, map.get(complement) - 1);
                count++;
            } else {
                // add current element in the map
                map.put(current, map.getOrDefault(current, 0) + 1);
            }
        }
        return count;
    }

    // Sort + Two Pointers. Time = O(N*log(N)); Space = O(1);
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < k) {
                left++;
            } else if (nums[left] + nums[right] > k) {
                right--;
            } else {
                left++;
                right--;
                count++;
            }
        }
        return count;
    }
}