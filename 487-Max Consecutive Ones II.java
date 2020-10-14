
import java.util.*;

class MaxConsecutiveOnesII {

    // Queue Store Index
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = 0;
        int k = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == 0) {
                queue.offer(r);
            }
            if (queue.size() > k) {
                l = queue.poll() + 1;
            }
            max = Math.max(max, r - l + 1);
        }
        return max;
    }

    // Two Pointers.
    public int findMaxConsecutiveOnesI(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int max = 0;
        int i = 0;
        int count = 0;
        
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == 0) {
                count++;
            }
            
            while (count > 1) {
                if (nums[i] == 0) {
                    count--;
                }
                i++;
            }
            max =  Math.max(max, j - i + 1); 
        }
        return max;
    }
}