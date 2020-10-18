import java.util.*;

class ContinuousSubarraySum {

    // Prefix Sum. Time = O(N^2); Space = O(N);
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 2; j <= nums.length; j++) {
                int sum = prefix[j] - prefix[i];
                if ((sum == k) || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // HashMap. Time = O(N); Space = O(min(N, K));
    public boolean checkSubarraySumI(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0)
                sum = sum % k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1)
                    return true;
            } else
                map.put(sum, i);
        }
        return false;
    }
}