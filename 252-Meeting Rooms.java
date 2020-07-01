import java.util.*;

class MeetingRooms {

    // idea is sort the array then compare the previous and current one 
    // Time O(N*log(N)) Space O(1)
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] one, int[] two) {
                if (one[0] == two[0]) {
                    return 0;
                }
                return one[0] < two[0] ? -1 : 1;
            }
        });
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}