import java.util.*;

class MissingNumber {

    // HashSet. Time = O(N); Space = O(N);
    public int missingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums)
            numSet.add(num);

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }

    // Bit Manipulation. Time = O(N); Space = O(1);
    public int missingNumberII(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    // Gauss' Formula. Time = O(N); Space = O(1);
    public int missingNumberI(int[] nums) {
        // if sum is not exceed maximum
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums)
            actualSum += num;
        return expectedSum - actualSum;
    }
}