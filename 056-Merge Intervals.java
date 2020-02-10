import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        // condition
        if (intervals.length <= 1)
            return intervals;

        // sort arrays
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return 0;
                }
                return a[0] < b[0] ? -1 : 1;
            }
        });

        // setup loop
        List<int[]> result = new LinkedList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else { // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}

// 1ms
class Solution1 {
    public int[][] merge(int[][] intervals) {
        // corner case check
        if (intervals == null || intervals.length <= 1 || intervals[0].length == 0) {
            return intervals;
        }

        boolean[] invalid = new boolean[intervals.length];
        int validCount = intervals.length; // count for result array's length

        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if ((intervals[i][1] >= intervals[j][1] && intervals[i][0] <= intervals[j][1])
                        || (intervals[j][1] >= intervals[i][1] && intervals[j][0] <= intervals[i][1])) {
                    invalid[i] = true;
                    intervals[j][0] = Math.min(intervals[i][0], intervals[j][0]);
                    intervals[j][1] = Math.max(intervals[i][1], intervals[j][1]);
                    break;
                }
            }
            if (invalid[i]) {
                validCount--;
            }
        }

        int[][] result = new int[validCount][2];
        int index = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (!invalid[i]) {
                result[index] = intervals[i];
                index++;
            }
        }

        return result;
    }
}