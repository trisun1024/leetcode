import java.util.*;

class MaximumLengthOfPairChain {

    // DP. Time = O(N^2); Space = O(N);
    public int findLongestChain(int[][] pairs) {
        // sort array by first element ascending order
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int N = pairs.length;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int longest = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                longest = Math.max(longest, dp[i]);
            }
        }
        return longest;
    }

    // Greedy. Time = O(N*log(N)); Space = O(N);
    public int findLongestChainI(int[][] pairs) {
        // sort array by last element ascending order
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int cur = Integer.MIN_VALUE;
        int res = 0;
        for (int[] pair : pairs) {
            if (cur < pair[0]) {
                cur = pair[1];
                res++;
            }
        }
        return res;
    }
}
