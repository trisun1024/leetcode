import java.util.*;

class DataStreamAsDisjointIntervals {

    class SummaryRanges {

        List<int[]> intervals;

        /** Initialize your data structure here. */
        public SummaryRanges() {
            this.intervals = new ArrayList<>();
        }

        public void addNum(int val) {
            boolean noInterval = true;
            for (int i = 0; i < this.intervals.size(); i++) {
                // if there is an overlap, break the loop and do nothing
                if (intervals.get(i)[0] <= val && intervals.get(i)[1] >= val) {
                    noInterval = false;
                    break;
                }

                // change the interval; [1,2], val = 3 -> [1,3]
                if (intervals.get(i)[1] + 1 == val) {
                    intervals.get(i)[1] = val;
                    noInterval = false;
                }

                // change the interval; [2,4], val = 3 -> [3,4]
                if (intervals.get(i)[0] - 1 == val) {
                    intervals.get(i)[0] = val;
                    noInterval = false;
                }

                // check if we should merge two intervals; [1,3], [3,4] - > [1,4]
                if (i != 0 && intervals.get(i - 1)[1] == intervals.get(i)[0]) {
                    intervals.get(i)[0] = intervals.get(i - 1)[0];
                    intervals.get(i)[1] = intervals.get(i)[1];
                    intervals.remove(i - 1);
                }

            } // if there isnt an interval for val, add to the list.
            if (noInterval) {
                intervals.add(new int[] { val, val });
            }
        }

        public int[][] getIntervals() {
            Collections.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            return intervals.toArray(new int[intervals.size()][]);
        }
    }

}
