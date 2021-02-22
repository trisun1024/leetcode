
class BrokenCalculator {

    // Word Backwards. Time = O(log(Y));
    public int brokenCalc(int X, int Y) {
        int ans = 0;
        while (Y > X) {
            ans++;
            if (Y % 2 == 1) {
                Y++;
            } else {
                Y /= 2;
            }
        }

        return ans + X - Y;
    }

    // Recursion.
    public int brokenCalcI(int X, int Y) {
        if (X == Y) {
            return 0;
        }
        if (Y < X) {
            return X - Y;
        }
        return 1 + (Y % 2 == 0 ? brokenCalcI(X, Y / 2) : brokenCalcI(X, Y + 1));
    }

}