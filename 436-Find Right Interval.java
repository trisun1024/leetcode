import java.util.*;

class FindRightInterval {

    // Brute Force. Time = O(N^2); Space = O(N);
    public int[] findRightInterval(int[][] intervals) {
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < intervals.length; j++) {
                if (intervals[j][0] >= intervals[i][1] && intervals[j][0] < min) {
                    min = intervals[j][0];
                    minIndex = j;
                }
            }
            res[i] = minIndex;
        }
        return res;
    }

    // Sorting & Scanning. Time = O(N^2); Space = O(N*log(N));
    public int[] findRightIntervalI(int[][] intervals) {
        int[] res = new int[intervals.length];
        Map<int[], Integer> hash = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            hash.put(intervals[i], i);
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < intervals.length; i++) {
            int min = Integer.MAX_VALUE;
            int minindex = -1;
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[j][0] >= intervals[i][1] && intervals[j][0] < min) {
                    min = intervals[j][0];
                    minindex = hash.get(intervals[j]);
                }
            }
            res[hash.get(intervals[i])] = minindex;
        }
        return res;
    }

    // Sorting + Binary Search. Time = O(N*log(N)); Space = O(N);
    public int[] binary_search(int[][] intervals, int target, int start, int end) {
        if (start >= end) {
            if (intervals[start][0] >= target) {
                return intervals[start];
            }
            return null;
        }
        int mid = (start + end) / 2;
        if (intervals[mid][0] < target) {
            return binary_search(intervals, target, mid + 1, end);
        } else {
            return binary_search(intervals, target, start, mid);
        }
    }

    public int[] findRightIntervalII(int[][] intervals) {
        int[] res = new int[intervals.length];
        HashMap<int[], Integer> hash = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            hash.put(intervals[i], i);
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = binary_search(intervals, intervals[i][1], 0, intervals.length - 1);
            res[hash.get(intervals[i])] = interval == null ? -1 : hash.get(interval);
        }
        return res;
    }

    // Two Arrays without Binary Search. Time = O(N*log(N)); Space = O(N);
    public int[] findRightIntervalIII(int[][] intervals) {
        int[][] endIntervals = Arrays.copyOf(intervals, intervals.length);
        HashMap<int[], Integer> hash = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            hash.put(intervals[i], i);
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Arrays.sort(endIntervals, (a, b) -> a[1] - b[1]);
        int j = 0;
        int[] res = new int[intervals.length];
        for (int i = 0; i < endIntervals.length; i++) {
            while (j < intervals.length && intervals[j][0] < endIntervals[i][1]) {
                j++;
            }
            res[hash.get(endIntervals[i])] = j == intervals.length ? -1 : hash.get(intervals[j]);
        }
        return res;
    }

}