import java.util.*;

class MergeIntervals {

    // sort array and combine
    public int[][] merge(int[][] intervals) {
        // condition
        if (intervals.length <= 1)
            return intervals;

        // sort arrays based on starting time 
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
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