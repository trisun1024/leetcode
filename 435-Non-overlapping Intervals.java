import java.util.*;

class Solution {

    // 435. Non-overlapping Intervals
    // Sort intervals on ending time, use DP to find overlapping intervals Time =
    // O(N*log(N)); Space = O(N);
    public int eraseOverlapIntervals(int[][] intervals) {
        // base case
        if (intervals.length == 0) {
            return 0;
        }
        // sort intervals on ending time
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int[] dp = new int[intervals.length];
        dp[0] = 1;
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (!isOverLapping(intervals[j], intervals[i])) {
                    max = Math.max(dp[j], max);
                    break;
                }
            }
            dp[i] = Math.max(max + 1, dp[i - 1]);
            count = Math.max(count, dp[i]);
        }
        return intervals.length - count;
    }

    private boolean isOverLapping(int[] a, int[] b) {
        return a[1] > b[0];
    }

    // Greedy Time = O(N*log(N)); Space = O(1)
    public int eraseOverlapIntervalsII(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int end = intervals[0][1];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }

    public int eraseOverlapIntervalsIII(int[][] intervals) {
        if (intervals.length < 2) {
            return 0;
        }
        // sort by starting time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int sum = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
            } else {
                if (intervals[i][1] < end) {
                    end = intervals[i][1];
                }
                sum++;
            }
        }
        return sum;
    }

}