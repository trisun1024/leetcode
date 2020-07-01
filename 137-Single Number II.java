import java.util.*;

class SingleNumberII {

    // HashSet solution Time = O(N); Space = O(N)
    public int singleNumberI(int[] nums) {
        Set<Long> set = new HashSet<>();
        long sumSet = 0, sumArray = 0;
        for (int n : nums) {
            sumArray += n;
            set.add((long) n);
        }
        for (Long s : set) {
            sumSet += s;
        }
        return (int) ((3 * sumSet - sumArray) / 2);
    }

    // HashMap similar

    // Bitwise Operator
    public int singleNumber(int[] nums) {
        int seenOnce = 0;
        int seenTwice = 0;
        for (int n : nums) {
            seenOnce = ~seenTwice & (seenOnce ^ n);
            seenTwice = ~seenOnce & (seenTwice ^ n);
        }
        return seenOnce;
    }

}