import java.util.*;

class SingleNumber {

    // HashSet. Time = O(N); Space = O(N);
    public int singleNumberI(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int n : nums) {
            sum += n;
            set.add(n);
        }
        int sumDouble = 0;
        for (Integer i : set) {
            sumDouble += i * 2;
        }
        return sumDouble - sum;
    }

    // Bit Manipulation. Time = O(N); Space = O(1);
    public int singleNumber(int[] nums) {
        int a = 0;
        for(int i : nums) {
            a ^= i;
        }
        return a;
    }
}