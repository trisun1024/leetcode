import java.util.*;

class ConvexPolygon {

    // Math.
    public boolean isConvexI(List<List<Integer>> points) {
        // For each set of three adjacent points A, B, C, find the cross product AB ·
        // BC. If the sign of
        // all the cross products is the same, the angles are all positive or negative
        // (depending on the
        // order in which we visit them) so the polygon is convex.
        boolean gotNegative = false;
        boolean gotPositive = false;
        int numPoints = points.size();
        int B, C;
        for (int A = 0; A < numPoints; A++) {
            // Trick to calc the last 3 points: n - 1, 0 and 1.
            B = (A + 1) % numPoints;
            C = (B + 1) % numPoints;

            int crossProduct = crossProductLength(points.get(A).get(0), points.get(A).get(1), points.get(B).get(0),
                    points.get(B).get(1), points.get(C).get(0), points.get(C).get(1));
            if (crossProduct < 0) {
                gotNegative = true;
            } else if (crossProduct > 0) {
                gotPositive = true;
            }
            if (gotNegative && gotPositive) {
                return false;
            }
        }

        // If we got this far, the polygon is convex.
        return true;
    }

    // Return the cross product AB x BC.
    // The cross product is a vector perpendicular to AB and BC having length |AB| *
    // |BC| * Sin(theta) and
    // with direction given by the right-hand rule. For two vectors in the X-Y
    // plane, the result is a
    // vector with X and Y components 0 so the Z component gives the vector's length
    // and direction.
    private int crossProductLength(int Ax, int Ay, int Bx, int By, int Cx, int Cy) {
        // Get the vectors' coordinates.
        int BAx = Ax - Bx;
        int BAy = Ay - By;
        int BCx = Cx - Bx;
        int BCy = Cy - By;

        // Calculate the Z coordinate of the cross product.
        return (BAx * BCy - BAy * BCx);
    }

    // 
    public boolean isConvex(List<List<Integer>> points) {
        if (points.size() < 4) {
            return true;
        }
        int sign = 0;
        List<Integer> prev1 = points.get(points.size() - 2);
        List<Integer> prev2 = points.get(points.size() - 1);
        for (List<Integer> point : points) {
            int z = getSign(prev1, prev2, point);
            if (sign == z || z == 0) {
                prev1 = prev2;
                prev2 = point;
            } else if (sign == 0) {
                sign = z;
                prev1 = prev2;
                prev2 = point;
            } else {
                return false;
            }
        }
        return true;
    }

    private int getSign(List<Integer> a, List<Integer> b, List<Integer> c) {
        int x1 = b.get(0) - a.get(0);
        int y1 = b.get(1) - a.get(1);
        int x2 = c.get(0) - b.get(0);
        int y2 = c.get(1) - b.get(1);
        int z = x1 * y2 - x2 * y1;
        if (z == 0) {
            return 0;
        }
        return z > 0 ? 1 : -1;
    }
}