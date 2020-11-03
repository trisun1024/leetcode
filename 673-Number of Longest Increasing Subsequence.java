import java.util.*;

class NumberOfLongestIncreasingSubsequence {

    // DP
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        // base case
        if (n <= 1) {
            return n;
        }
        // build two arrays one for storing longest, one for counts 
        int[] longest = new int[n];
        int[] counts = new int[n];
        Arrays.fill(counts, 1);
        int max = 0;
        for (int i = 0; i < n; i++) {
            longest[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (longest[j] >= longest[i]) {
                        longest[i] = longest[j] + 1;
                        counts[i] = counts[j];
                    } else if (longest[j] + 1 == longest[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
            max = Math.max(longest[i], max);
        }
       
        int res = 0; 
        for (int i = 0; i < n; i++) {
            if (longest[i] == max) {
                res += counts[i];
            }
        }
        return res;
    }
}