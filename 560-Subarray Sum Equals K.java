import java.util.*;

class SubarraySumEqualsK {

    // Prefix Sum. Time = O(N^2); Space = O(N);
    public int subarraySum(int[] nums, int k) {
        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = nums[i - 1] + prefix[i - 1];
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= nums.length; j++) {
                if ((prefix[j] - prefix[i]) == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySumI(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }

    // HashMap.
    public int subarraySumII(int[] nums, int k) {
        int result = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                result += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}