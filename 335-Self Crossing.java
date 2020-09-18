import java.util.*;

class SelfCrossing {

    // Time = O(); Space = O(1);
    public boolean isSelfCrossing(int[] x) {
        int max = 6;
        LinkedList<Point> points = new LinkedList<>();
        Point prev = new Point(0, 0);
        points.add(prev);
        for (int i = 0; i < x.length; i++) {
            Point cur;
            if (i % 4 == 0) {
                cur = new Point(prev.x - x[i], prev.y);
            } else if (i % 4 == 1) {
                cur = new Point(prev.x, prev.y - x[i]);
            } else if (i % 4 == 2) {
                cur = new Point(prev.x + x[i], prev.y);
            } else {
                cur = new Point(prev.x, prev.y + x[i]);
            }
            if (points.size() > 3) {
                for (int j = 0; j < 3; j++) {
                    if (isIntersecting(points.get(j), points.get(j + 1), prev, cur)) {
                        return true;
                    }
                }
                if (points.size() == max) {
                    points.removeFirst();
                }
            }
            points.add(cur);
            prev = cur;
        }
        return false;
    }

    private boolean isIntersecting(Point p1, Point q1, Point p2, Point q2) {
        if (p2 == q1) {
            return false;
        }
        int o1 = orient(p1, q1, p2);
        int o2 = orient(p1, q1, q2);
        int o3 = orient(p2, q2, p1);
        int o4 = orient(p2, q2, q1);
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        // If O1 is 0 that means the 3 points are on the same line already, onSeg is
        // trying to see if q1 is in between p1 and p2. If so, it is colleniar
        if (o1 == 0 && onseg(p1, p2, q1)) {
            return true;
        }
        if (o2 == 0 && onseg(p1, q2, q1)) {
            return true;
        }
        if (o3 == 0 && onseg(p2, p1, q2)) {
            return true;
        }
        if (o4 == 0 && onseg(p2, q1, q2)) {
            return true;
        }
        return false;
    }

    private int orient(Point a, Point b, Point c) {
        int val = (b.y - a.y) * (c.x - b.x) - (c.y - b.y) * (b.x - a.x);
        if (val == 0) {
            return 0;
        }
        return val < 0 ? 1 : 2;
    }

    private boolean onseg(Point p, Point q, Point r) {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(p.y, r.y)
                && q.y >= Math.min(p.y, r.y))
            return true;

        return false;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 
    public boolean isSelfCrossingI(int[] x) {
        int n = x.length;
        if (n <= 3)
            return false;
        int i = 2;
        // keep spiraling outward
        while (i < n && x[i] > x[i - 2])
            i++;
        // spiraling
        if (i >= n)
            return false;
        // transite from outward to inward
        if ((i == 3 && x[i] == x[i - 2]) || (i > 3 && x[i] >= x[i - 2] - x[i - 4]))
            x[i - 1] -= x[i - 3];
        // keep spiraling inward
        i++;
        while (i < n) {
            if (x[i] >= x[i - 2])
                return true;
            i++;
        }
        return false;
    }
}