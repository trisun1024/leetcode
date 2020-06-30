import java.util.*;

class Solution {

    // Brute Force. Time = O(N^2);
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs((long) nums[i] - (long) nums[j]) <= t && Math.abs(i - j) <= k) {
                    return true;
                }
            }
        }
        return false;
    }

    // Bucket. Time = O(N); Space = O(min(N,K));
    public boolean containsNearbyAlmostDuplicateI(int[] nums, int k, int t) {
        if (t < 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; i++) {
            long m = getID(nums[i], w);
            if (map.containsKey(m)) {
                return true;
            }
            if (map.containsKey(m - 1) && Math.abs(nums[i] - map.get(m - 1)) < w) {
                return true;
            }
            if (map.containsKey(m + 1) && Math.abs(nums[i] - map.get(m + 1)) < w) {
                return true;
            }
            map.put(m, (long) nums[i]);
            if (i >= k) {
                map.remove( getID(nums[i - k], w));
            }
        }
        return false;
    }

    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }
}