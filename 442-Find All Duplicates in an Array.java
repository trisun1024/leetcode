import java.util.*;

class AllDuplicatesInArray {

    // HashSet Time = O(N); Space = O(N);
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> found = new HashSet<>();
        for (int n : nums) {
            if (!found.contains(n)) {
                found.add(n);
            } else {
                res.add(n);
            }
        }
        return res;
    }

    // One-time Time = O(N); Space = O(1); require numbers are not larger than the
    // length
    public List<Integer> findDuplicatesI(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int n : nums) {
            if (nums[Math.abs(n) - 1] < 0) {
                res.add(Math.abs(n));
            }
            nums[Math.abs(n) - 1] *= -1;
        }
        return res;
    }
}