
class SumOfSquareNumbers {

    // Binary Search.
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            int b = c - (int) (a * a);
            if (binarySearch(0, b, b)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(long l, long r, int n) {
        if (l > r) {
            return false;
        }
        long m = l + (r - l) / 2;
        if (m * m == n) {
            return true;
        }
        if (m * m > n) {
            return binarySearch(l, m - 1, n);
        } else {
            return binarySearch(m + 1, r, n);
        }
    }

    // Fermat Theorem.
    public boolean judgeSquareSumI(int c) {
        for (int i = 2; i * i <= c; i++) {
            int count = 0;
            if (c % i == 0) {
                while (c % i == 0) {
                    count++;
                    c /= i;
                }
                if (i % 4 == 3 && count % 2 != 0)
                    return false;
            }
        }
        return c % 4 != 3;
    }
}
