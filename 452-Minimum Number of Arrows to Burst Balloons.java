import java.util.*;

class MinimumNumberArrowstoBurstBalloons {

    // Greedy. Time = O(N*log(N)); Space = O(1);
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> {
            if (a[1] > b[1]) {
                return 1;
            } else {
                return -1;
            }
        });

        int num = 0;
        int prevEnd = points[0][1];
        int i = 1;
        while (i < points.length) {
            if (points[i][0] > prevEnd) {
                num++;
                prevEnd = points[i][1];
            }
            i++;
        }
        return num + 1;
    }
}