import java.util.*;

class PerfectRectangle {

    // Too hard
    public boolean isRectangleCover(int[][] rectangles) {
        Set<String> res = new HashSet<>();

        int bottomLeftX = Integer.MAX_VALUE;
        int bottomLeftY = Integer.MAX_VALUE;
        int topRightX = Integer.MIN_VALUE;
        int topRightY = Integer.MIN_VALUE;
        int area = 0;

        for (int[] rect : rectangles) {
            bottomLeftX = Math.min(bottomLeftX, rect[0]);
            bottomLeftY = Math.min(bottomLeftY, rect[1]);
            topRightX = Math.max(topRightX, rect[2]);
            topRightY = Math.max(topRightY, rect[3]);

            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);

            String bottomLeft = rect[0] + ":" + rect[1];
            String bottomRight = rect[2] + ":" + rect[1];
            String topLeft = rect[0] + ":" + rect[3];
            String topRight = rect[2] + ":" + rect[3];
            if (!res.add(bottomLeft))
                res.remove(bottomLeft);
            if (!res.add(bottomRight))
                res.remove(bottomRight);
            if (!res.add(topLeft))
                res.remove(topLeft);
            if (!res.add(topRight))
                res.remove(topRight);
        }

        if (!res.contains(bottomLeftX + ":" + bottomLeftY) || !res.contains(topRightX + ":" + bottomLeftY)
                || !res.contains(bottomLeftX + ":" + topRightY) || !res.contains(topRightX + ":" + topRightY)
                || res.size() != 4)
            return false;

        return area == (topRightX - bottomLeftX) * (topRightY - bottomLeftY);
    }
}