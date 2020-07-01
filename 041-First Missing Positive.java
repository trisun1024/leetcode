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

    // put
    public int firstMissingPositive(int[] nums) {
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
}