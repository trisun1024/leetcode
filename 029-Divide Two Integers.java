class Solution {
    public int divide(int dividend, int divisor) {
        // boarder
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
            return Integer.MAX_VALUE;
        // check +1/-1
        int sign = ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) ? -1 : 1;

        // transfer to long, make sure result in int.size
        long divisor_abs = Math.abs((long) divisor), dividend_abs = Math.abs((long) dividend);

        long res = longDivide(dividend_abs, divisor_abs);
        // res reach int max return max or min; else normal;
        if (res > Integer.MAX_VALUE)
            return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        else
            return sign == 1 ? (int) res : (int) (-res);
    }

    // Recursion
    public long longDivide(long dividend_abs, long divisor_abs) {
        if (dividend_abs < divisor_abs)
            return 0;

        long sum = divisor_abs;
        long multiple = 1;

        while ((sum + sum) < dividend_abs) {
            sum += sum;
            multiple += multiple;
        }

        return multiple + longDivide(dividend_abs - sum, divisor_abs);
    }
}