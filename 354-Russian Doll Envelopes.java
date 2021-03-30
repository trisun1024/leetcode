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

    private int longestSubsequences(int[] arr) {
        int[] dp = new int[arr.length];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int j = bs(dp, 0, max, arr[i]);
            if (j < 0) {
                j = -(j + 1);
            }
            dp[j] = arr[i];
            if (j == max) {
                max++;
            }
        }
        return max;
    }

    private int bs(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1); // key not found.
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