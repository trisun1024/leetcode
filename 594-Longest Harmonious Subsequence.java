import java.util.*;

class LongestHarmoniousSubsequence {

    // HashMap.
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
            if (map.containsKey(n + 1)) {
                max = Math.max(max, map.get(n) + map.get(n + 1));
            }
            if (map.containsKey(n - 1)) {
                max = Math.max(max, map.get(n) + map.get(n - 1));
            }
        }
        return max;
    }

    // Sorting + Two Pointers.
    public int findLHSI(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        int start = 0;
        int next = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] - nums[start] > 1) {
                start = next;
            }
            if (nums[i] != nums[i - 1]) {
                next = i;
            }
            if (nums[i] - nums[start] == 1) {
                max = Math.max(max, i - start + 1);
            }
        }
        return max;
    }
}
