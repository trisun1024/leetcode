import java.util.*;

class NumberOfBoomerangs {

    // HashMap. Time = (N^2); Space = O(N);
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[0].length; j++) {
                if (i == j) {
                    continue;
                }
                int dist = getDistance(points[i], points[j]);
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }
            for (int val : map.values()) {
                count += val * (val - 1);
            }
            map.clear();
        }
        return count;
    }

    private int getDistance(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        return dx * dx + dy * dy;
    }
}