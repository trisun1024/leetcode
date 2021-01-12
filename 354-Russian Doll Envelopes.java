import java.util.*;

class RussianDollEnvelopes {

    // Sort + Longest Increasing Subsequence. Time = O(N*log(N)); Space = O(N);
    public int maxEnvelopes(int[][] envelopes) {
        // sort first dim with increasing and second with decreasing
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return b[1] - a[1];
                } else {
                    return a[0] - b[0];
                }
            }
        });
        int[] secondDim = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            secondDim[i] = envelopes[i][1];
        }
        return longestSubsequences(secondDim);
    }

    private int longestSubsequences(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    // DP. Time = O(N^2);
    public int maxEnvelopesI(int[][] envelopes) {
        // sort array with increasing width
        Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
        int m = envelopes.length;
        int[] dp = new int[m];
        int max = 0;
        for (int i = 0; i < m; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}