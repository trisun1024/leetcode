import java.util.*;

class MinimumNumberArrowstoBurstBalloons {

    // Greedy. Time = O(N*log(N)); Space = O(1);
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int arrows = 1;
        int xStart = points[0][1];
        int xEnd = points[0][1];
        int firstEnd = points[0][1];
        for (int[] p : points) {
            xStart = p[0];
            xEnd = p[1];
            if (firstEnd < xStart) {
                arrows++;
                firstEnd = xEnd;
            }
        }
        return arrows;
    }
}