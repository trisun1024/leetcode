// Greedy
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0], newEnd = newInterval[1];
        int index = 0, n = intervals.length;
        LinkedList<int[]> result = new LinkedList<>();

        // add all intervals
        while (index < n && newStart > intervals[index][0]) {
            result.add(intervals[index++]);
        }

        // add newInterval
        int[] interval = new int[2];
        if (result.isEmpty() || result.getLast()[1] < newStart) {
            result.add(newInterval);
        } else {
            interval = result.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            result.add(interval);
        }

        // check others
        while (index < n) {
            interval = intervals[index++];
            int start = interval[0], end = interval[1];
            if (result.getLast()[1] < start) {
                result.add(interval);
            } else {
                interval = result.removeLast();
                interval[1] = Math.max(interval[1], end);
                result.add(interval);
            }
        }

        return result.toArray(new int[result.size()][2]);
    }
}