import java.util.*;

class SquaresOfSortedArray {

    // Sort. Time = O(N*log(N)); Space = O(N);
    public int[] sortedSquares(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; ++i) {
            ans[i] = nums[i] * nums[i];
        }

        Arrays.sort(ans);
        return ans;
    }

    // Two Pointers. Time = O(N); Space = O(N);
    public int[] sortedSquaresI(int[] nums) {
        int N = nums.length;
        int j = 0;
        while (j < N && nums[j] < 0) {
            j++;
        }
        int i = j - 1;
        int[] ans = new int[N];
        int t = 0;
        while (i >= 0 && j < N) {
            if (nums[i] * nums[i] < nums[j] * nums[j]) {
                ans[t++] = nums[i] * nums[i];
                i--;
            } else {
                ans[t++] = nums[j] * nums[j];
                j++;
            }
        }
        while (i >= 0) {
            ans[t++] = nums[i] * nums[i];
            i--;
        }
        while (j < N) {
            ans[t++] = nums[j] * nums[j];
            j++;
        }
        return ans;
    }
}