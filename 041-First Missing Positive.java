import java.util.*;

class FirstMissingPositive {

    // Array sort then search. Time = O(N*log(N));
    public int firstMissingPositiveI(int[] nums) {
        Arrays.sort(nums);
        int m = 1;
        int prev = 0;
        for (int n : nums) {
            if (n > 0) {
                if (m < n) {
                    return m;
                }
                if (prev != n) {
                    m++;
                    prev = n;
                }
            }
        }
        return m;
    }

    // Array check. Time = O(N); Space = O(N);
    public int firstMissingPositiveII(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        boolean[] visited = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 1 && nums[i] <= n)
                visited[nums[i]] = true;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return n + 1;
    }

    // Cycle Sort. Time = O(N); Space = O(1);
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            // curr-1 is the next index to swap and must be inside bound
            while (curr - 1 >= 0 && curr - 1 < nums.length && nums[curr - 1] != curr) {
                int temp = nums[curr - 1];
                nums[curr - 1] = curr;
                curr = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return nums.length + 1;
    }
}