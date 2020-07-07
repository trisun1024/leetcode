import java.util.*;
class SingleNumberIII {

    // HashSet
    public int[] singleNumberI(int[] nums) {
        Set<Integer> found = new HashSet<>();
        for (int n : nums) {
            if (!found.contains(n)) {
                found.add(n);
            } else {
                found.remove(n);
            }
        }
        int[] res = new int[found.size()];
        int i = 0;
        for (Integer n : found) {
            res[i++] = n;
        }
        return res;
    }

    // Bit
    public int[] singleNumber(int[] nums) {
        // difference between two numbers (x and y) which were seen only once
        int bitmask = 0;
        for (int num : nums)
            bitmask ^= num;

        // rightmost 1-bit diff between x and y
        int diff = bitmask & (-bitmask);

        int x = 0;
        // bitmask which will contain only x
        for (int num : nums)
            if ((num & diff) != 0)
                x ^= num;

        return new int[] { x, bitmask ^ x };
    }
}
