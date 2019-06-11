class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
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