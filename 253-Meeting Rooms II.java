import java.util.*;

class Solution {

    // sort array in order of the first
    // use min heap to store the end of time. maintain the minimum on the top.
    // if start time greater than min, poll min
    // Time O(N*log(N)) Space O(N)
    public int minMeetingRoomsII(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return 0;
                }
                return o1[0] < o2[0] ? -1 : 1;
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>(intervals.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if (i1.equals(i2)) {
                    return 0;
                }
                return i1 < i2 ? -1 : 1;
            }
        });
        pq.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }

    // Chronological Ordering
    // Time O(N*log(N)) Space O(N)
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        int len = intervals.length;
        int[] starts = new int[len];
        int[] ends = new int[len];
        for (int i = 0; i < len; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int room = 0, prev = 0;
        for (int i = 0; i < len; i++) {
            room++;
            if (starts[i] >= ends[prev]) {
                room--;
                prev++;
            }
        }
        return room;
    }
}