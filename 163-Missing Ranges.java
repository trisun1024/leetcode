import java.util.*;
 
class MissingRanges {

    // Linear Scan. Time = O(N);
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        for (int num : nums) {
            if (num > lower) {
                res.add(lower + ((num - 1 > lower) ? "->" + (num - 1) : ""));
            }
            if (num == upper) {
                return res;
            }
            lower = num + 1;
        }
        if (lower <= upper) {
            res.add(lower + ((upper > lower) ? "->" + upper : ""));
        }
        return res;
    }
}