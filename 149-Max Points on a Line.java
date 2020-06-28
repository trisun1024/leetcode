import java.util.*;

class Solution {

    // Greatest Common Divisor. Time = O(N^2);
    public int maxPointsII(int[][] points) {
        int len = points.length;
        if (len <= 2) {
            return len;
        }
        int max = 0;
        for (int i = 0; i < len - 1; i++) {
            Map<Long, Integer> map = new HashMap<>();
            int samePoint = 0;
            int sameLine = 1;
            for (int j = i + 1; j < len; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    ++samePoint;
                    continue;
                }
                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];
                int gcd = generateGCD(dx, dy);

                int commX = dx / gcd, commY = dy / gcd;
                long hash = myHashCode(commX, commY);
                Integer val = map.get(hash);
                if (val != null) {
                    map.put(hash, val + 1);
                    ++val;
                } else {
                    val = 2;
                    map.put(hash, val);
                }
                sameLine = Math.max(sameLine, val);
            }
            max = Math.max(max, samePoint + sameLine);
        }

        return max;
    }

    private int generateGCD(int a, int b) {
        int r = 0;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    private long myHashCode(long dx, long dy) {
        return (dx << 32) ^ dy;
    }

    // Cross Product. Time = O(N^3);
    public int maxPoints(int[][] points) {
        int res = 0;
        int n = points.length;
        for (int i = 0; i < n; ++i) {
            int duplicate = 1;
            for (int j = i + 1; j < n; ++j) {
                int cnt = 0;
                long x1 = points[i][0], y1 = points[i][1];
                long x2 = points[j][0], y2 = points[j][1];
                if (x1 == x2 && y1 == y2) {
                    ++duplicate;
                    continue;
                }
                for (int k = 0; k < n; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    if (x1 * y2 + x2 * y3 + x3 * y1 - x3 * y2 - x2 * y1 - x1 * y3 == 0) {
                        ++cnt;
                    }
                }
                res = Math.max(res, cnt);
            }
            res = Math.max(res, duplicate);
        }
        return res;
    }

}