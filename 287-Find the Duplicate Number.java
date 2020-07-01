import java.util.*;

class FindDuplicateNumber {

    // sorting
    // Time O(N*log(N)) Space O(1)
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    // set
    // Time O(N) Space O(N)
    public int findDuplicateII(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int n : nums) {
            if (seen.contains(n)) {
                return n;
            }
            seen.add(n);
        }
        return -1;
    }

    // Floyd's Tortoise and Hare
    // Time O(N) Space O(1)
    public int findDuplicateIII(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
        return ptr1;
    }
}