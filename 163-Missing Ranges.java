import java.util.*;

// extreme case Integer.MAX_VALUE
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        long Lower = (long) lower;
        long Upper = (long) upper;
        for (int num : nums) {
            long justBelow = (long) num - 1;
            if (justBelow == Lower)
                res.add(String.valueOf(justBelow));
            else if (justBelow > Lower)
                res.add(Lower + "->" + justBelow);
            if (num == Integer.MAX_VALUE)
                return res;
            Lower = (long) num + 1;
        }
        if (Lower == Upper)
            res.add(String.valueOf(Upper));
        else if (Lower < Upper)
            res.add(Lower + "->" + Upper);
        return res;
    }
}