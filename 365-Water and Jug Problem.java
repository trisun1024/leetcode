import java.util.*;

class WaterAndJugProblem {

    // Time = O(log(Y)-1); Space = O(1);
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        } else {
            return (x == y && x == z) || z % gcd(x, y) == 0;
        }
    }

    private int gcd(int x, int y) {
        while (y > 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }

    // HashSet
    public boolean canMeasureWaterI(int x, int y, int z) {
        if (z > x + y)
            return false;
        if (z == x || z == y || z == x + y || z == 0)
            return true;
        if (x > y) {
            int tmp = y;
            y = x;
            x = tmp;
        }
        if (x == 1)
            return true;
        if (x == 0)
            return false;
        Set<Integer> set = new HashSet<>();
        int curY = 0;
        while (curY + x <= y) {
            curY += x; // fill(x) and pour(x->y);
            if (curY == z)
                return true;
            set.add(curY);
        }
        set.add(curY + x); // fill(x) and pour(x->y);
        if (curY + x == z)
            return true;
        curY = y; // y is full
        set.add(curY + x); // fill(x)
        do {
            set.add(curY); // empty(x)
            if (curY >= x) {
                curY -= x; // pour(y->x)
            } else {
                if (curY + y == z)
                    return true;
                set.add(curY + y);
                curY = y + curY - x; // pour(y->x) and fill(y) and pour(y->x)
            }
            if (curY == z)
                return true;

        } while (!set.contains(curY));
        return false;
    }
}