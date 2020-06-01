import java.util.*;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        if (nums1.length > nums2.length) {
            intersection(nums2, nums1);
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        Set<Integer> res = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                res.add(i);
            }
        }
        int[] out = new int[res.size()];
        int j = 0;
        for (Integer i : res) {
            out[j++] = i;
        }
        return out;
    }
}